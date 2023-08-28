package br.fiap.projeto.contexto.pagamento.domain.service;


import br.fiap.projeto.contexto.pagamento.domain.Pagamento;
import br.fiap.projeto.contexto.pagamento.application.rest.response.PedidoAPagarDTO;
import br.fiap.projeto.contexto.pagamento.application.rest.response.PagamentoAprovadoDTO;
import br.fiap.projeto.contexto.pagamento.application.rest.response.PagamentoDTO;
import br.fiap.projeto.contexto.pagamento.domain.enums.StatusPagamento;
import br.fiap.projeto.contexto.pagamento.domain.port.repository.PagamentoRepositoryPort;
import br.fiap.projeto.contexto.pagamento.domain.port.service.PagamentoServicePort;
import br.fiap.projeto.contexto.pagamento.domain.service.exceptions.ResourceAlreadyInProcessException;
import br.fiap.projeto.contexto.pagamento.domain.service.exceptions.ResourceNotFoundException;
import br.fiap.projeto.contexto.pagamento.domain.service.exceptions.UnprocessablePaymentException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class DomainPagamentoService implements PagamentoServicePort {

    private final PagamentoRepositoryPort pagamentoRepositoryPort;


    public DomainPagamentoService(PagamentoRepositoryPort pagamentoRepositoryPort) {
        this.pagamentoRepositoryPort = pagamentoRepositoryPort;
    }

    @Override
    public Page<PagamentoDTO> buscaListaDePagamentos(Pageable pageable) {
        Page<Pagamento> listaDePagamentos = pagamentoRepositoryPort.findAll(pageable);
        return listaDePagamentos.map(PagamentoDTO::new);
    }

    @Override
    public PagamentoDTO findByCodigo(UUID codigo) {
        Optional<Pagamento> possivelPagamento = Optional.ofNullable(pagamentoRepositoryPort.findByCodigo(codigo));
        Pagamento pagamento = possivelPagamento.orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado"));
        return new PagamentoDTO(pagamento);
    }

    @Override
    public PagamentoDTO findByCodigoPedido(String codigoPedido) {
        Optional<Pagamento> possivelPagamento = Optional.ofNullable(pagamentoRepositoryPort.findByCodigoPedido(codigoPedido));
        Pagamento pagamento = possivelPagamento.orElseThrow(() -> new ResourceNotFoundException("Pedido com ID " + codigoPedido + " não foi encontrado."));
        return new PagamentoDTO(pagamento);
    }

    @Override
    public Page<PagamentoDTO> findByStatus(StatusPagamento status, Pageable pageable) {
        Page<Pagamento> listaDePagamentos = pagamentoRepositoryPort.findByStatusPagamento(status, pageable);
        return listaDePagamentos.map(PagamentoDTO::new);
    }

    @Override
    public Page<PagamentoAprovadoDTO> findByStatusAprovado(Pageable pageable) {
        Page<Pagamento> listaDePagamentos = pagamentoRepositoryPort.findByStatusPagamento(StatusPagamento.APPROVED, pageable);
        return listaDePagamentos.map(PagamentoAprovadoDTO::new);
    }

    /**
     * Recebe os pedidos da API de pedidos recebidos e que vão para o gateway de pagamento,
     * Ao receber a lista de Pedidos com status RECEBIDO, um código de pagamento é gerado
     * Simulando o consumo de dados do domínio de Pedido que será usado dentro do domínio de Pagamento
     * @param pedidosAPagarDTO
     */
    @Override
    public void recebePedidosAPagar(PedidoAPagarDTO pedidosAPagarDTO) {
        Pagamento novoPagamento = new Pagamento(pedidosAPagarDTO);
        pagamentoRepositoryPort.salvaPedidosAPagar(novoPagamento);
        System.out.println("DOMAIN SERVICE: Novo pagamento criado para o pedido: " + novoPagamento.getCodigoPedido());
    }

    /**
     * Busca todos os Pagamentos que estão com Status PENDING - Simula os pagamentos que ainda não foram processados
     * pelo Gateway de Pagamento
     * @return
     */
    @Override
    public List<PedidoAPagarDTO> buscaPedidosAPagar() {
       List<Pagamento> listaPedidosAPagar = pagamentoRepositoryPort.findByStatusPagamento(StatusPagamento.PENDING);
       return listaPedidosAPagar.stream().map(PedidoAPagarDTO::new).collect(Collectors.toList());
    }

    /**
     * Persiste o código do Pedido recebido e ao persistir o pagamento é criado para aquele pedido
     * TODO elaborar fluxo de exeções que não estão sendo tratadas
     * @param pedidoAPagarDTO
     * @return
     */
    @Override
    public PedidoAPagarDTO criaPagamentoViaGateway(PedidoAPagarDTO pedidoAPagarDTO)  {
        verificaSeJaExistePagamentoParaOPedido(pedidoAPagarDTO);
        return new PedidoAPagarDTO(new PagamentoDTO(pedidoAPagarDTO));
    }

    private void verificaSeJaExistePagamentoParaOPedido(PedidoAPagarDTO pedidoAPagarDTO) {
        Pagamento pagamento = pagamentoRepositoryPort.findByCodigoPedido(pedidoAPagarDTO.getCodigoPedido());
        if((pagamento.getCodigoPedido().isEmpty()) || (pagamento.getCodigoPedido() == null)){
            System.out.println("Um pagamento está sendo criado para o pedido: " + pedidoAPagarDTO.getCodigoPedido());
            pagamentoRepositoryPort.salvaPagamento(new Pagamento(pedidoAPagarDTO));
        }else{
            throw new ResourceAlreadyInProcessException("Pedido:  " + pedidoAPagarDTO.getCodigoPedido() +" já possui pagamento PENDNETE.");
        }
    }

    /**
     * Simula a lógica da Domain Service usando o gateway
     * Para sua implementação é necessária a instalação do SDK do MP
     * o payload é definido de forma específica para o Gateway
     */
    @Override
    public void enviaGatewayDePagamento(PedidoAPagarDTO pedidoAPagarDTO) {
        System.out.println("verficando qual Gateway será usado...");
        System.out.println("Criando payload de acordo com o Gateway do Mercado Pago");
        System.out.println("Enviando ao Mercado Pago a request de pagamento...");
        System.out.println("Chamando evento que cria código para Pagamento do pedido: " + pedidoAPagarDTO.getCodigoPedido());

        analisaStatusDoPagamento(pedidoAPagarDTO.getStatusPagamento(), StatusPagamento.IN_PROCESS, new Pagamento(pedidoAPagarDTO));

        System.out.println("Aguardando retorno do Gateway com o Status do pagamento enviado.");
    }

    /**
     * Cria um novo Pagamento com base no payload da requisição
     *
     * @param pagamentoDTO
     * @return
     */
    @Override
    public PagamentoDTO criaPagamento(PagamentoDTO pagamentoDTO)  {
        System.out.println(pagamentoDTO.getStatus() + " - Status passado na Request");

        if(!podeIniciarPagamento(pagamentoDTO)){
            throw new UnprocessablePaymentException("Pagamento não pode ser processado. STATUS: " + pagamentoDTO.getStatus() + " Pedido associado: " + pagamentoDTO.getCodigoPedido());
        }

        pagamentoRepositoryPort.findByCodigo(pagamentoDTO.getCodigo());
        iniciaStatusPagamento(pagamentoDTO);

        Pagamento novoPagamento = new Pagamento(pagamentoDTO);
        pagamentoRepositoryPort.salvaPagamento(novoPagamento);
        return new PagamentoDTO(novoPagamento);

    }


    /**
     * Verifica o status atual do Pagamento e atualiza conforme a requisição recebida<br/>
     * RN - Apenas pagamentos com status EM PROCESSAMENTO podem ir para status:<br/>
     *      APROVADO, CANCELADO ou REJEITADO<br/>
     * RN - Pagamentos Aprovados alimentam a fila de Pedidos a serem preparados<br/>
     * @param statusAtual
     * @param pagamentoDTO
     * @param statusRequest
     */
    @Override
    public void lidaStatusPagamento(StatusPagamento statusAtual, PagamentoDTO pagamentoDTO, StatusPagamento statusRequest) {
        System.out.println("Status entrando no LidaStatus "+ statusAtual + " Pagamento: " + pagamentoDTO.getCodigo());

        Pagamento pagamentoEmAndamento = pagamentoRepositoryPort.findByCodigo(pagamentoDTO.getCodigo());

        analisaStatusDoPagamento(statusAtual, statusRequest, pagamentoEmAndamento);
        pagamentoRepositoryPort.salvaStatus(pagamentoEmAndamento);
    }


    private void analisaStatusDoPagamento(StatusPagamento statusAtual, StatusPagamento statusRequest, Pagamento pagamentoEmAndamento) {
        if(pagamentoEmAndamento.podeSerProcessado(statusAtual, statusRequest)){
            pagamentoEmAndamento.colocaEmProcessamento();
        }
        if(pagamentoEmAndamento.podeSerAprovado(statusAtual, statusRequest)) {
              pagamentoEmAndamento.aprovaPagamento();
        }
        if(pagamentoEmAndamento.podeSerCancelado(statusAtual, statusRequest)){
             pagamentoEmAndamento.cancelaPagamento();
        }
        if(pagamentoEmAndamento.podeSerRejeitado(statusAtual, statusRequest)){
              pagamentoEmAndamento.rejeitaPagamento();
        }
        pagamentoRepositoryPort.salvaStatus(pagamentoEmAndamento);
    }

    /**
     * Realiza o processamento do Pagamento com base no Status do pagamento<br/>
     * RN - Apenas pagamentos com Status PENDENTE podem ir para EM PROCESSAMENTO<br/>
     * @param codigo
     * @param statusRequest
     */
    @Override
    public void processaPagamento(UUID codigo, StatusPagamento statusRequest) {
        Pagamento pagamento = pagamentoRepositoryPort.findByCodigo(codigo);
        //analisa ql é o status retornado pelo gateway e ajusta o proximo status
        lidaStatusPagamento(pagamento.getStatus(), new PagamentoDTO(pagamento), statusRequest);
    }

    /**
     * Verifica se o número do pedido passado existe e se o pedido possui status PENDENTE
     * RN - Um pedido no estado PENDENTE não pode ter seu pagamento iniciado novamente
     *
     * @param pagamentoDTO
     */
    @Override
    public void verificaNumeroDoPedido(PagamentoDTO pagamentoDTO) {
        findByCodigoPedido(pagamentoDTO.getCodigoPedido());
        verificaStatusPendente(pagamentoDTO.getStatus());
    }

    /**
     * Verifica se o Pagamento esta no estado PENDENTE<br/>
     * RN - Um pedido no estado PENDENTE só poderá ir para EM PROCESSAMENTO
     *
     * @param status
     */
    private void verificaStatusPendente(StatusPagamento status) {
        if(!status.equals(StatusPagamento.PENDING)) {
            throw new ResourceNotFoundException("Pedido encontra-se : " + status.name());
        }
    }

    //TODO enviar verificação para classe PagamentoDTO
    private boolean podeIniciarPagamento(PagamentoDTO pagamentoDTO) {
       return pagamentoDTO.getStatus().equals(StatusPagamento.PENDING);
    }

    //TODO enviar verificação para classe PagamentoDTO
    private void iniciaStatusPagamento(PagamentoDTO pagamentoDTO){
        pagamentoDTO.setStatus(StatusPagamento.PENDING);
    }

}
