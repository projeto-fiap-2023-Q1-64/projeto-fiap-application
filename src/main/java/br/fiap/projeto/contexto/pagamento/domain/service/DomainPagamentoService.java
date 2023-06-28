package br.fiap.projeto.contexto.pagamento.domain.service;


import br.fiap.projeto.contexto.pagamento.domain.Pagamento;
import br.fiap.projeto.contexto.pagamento.application.rest.response.CompraAPagarDTO;
import br.fiap.projeto.contexto.pagamento.application.rest.response.PagamentoAprovadoDTO;
import br.fiap.projeto.contexto.pagamento.application.rest.response.PagamentoDTO;
import br.fiap.projeto.contexto.pagamento.domain.enums.StatusPagamento;
import br.fiap.projeto.contexto.pagamento.domain.port.repository.PagamentoRepositoryPort;
import br.fiap.projeto.contexto.pagamento.domain.port.service.PagamentoServicePort;
import br.fiap.projeto.contexto.pagamento.domain.service.exceptions.ResourceNotFoundException;
import br.fiap.projeto.contexto.pagamento.domain.service.exceptions.UnprocessablePaymentException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;



import java.util.Optional;
import java.util.UUID;

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
    public PagamentoDTO findByCodigoPedido(Long codigoPedido) {
        Optional<Pagamento> possivelPagamento = pagamentoRepositoryPort.findByCodigoPedido(codigoPedido);
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
        if(podeSerAprovado(statusAtual, statusRequest)) {
              pagamentoEmAndamento.aprovaPagamento();
        }
        if(podeSerCancelado(statusAtual, statusRequest)){
             pagamentoEmAndamento.cancelaPagamento();
        }
        if(podeSerRejeitado(statusAtual, statusRequest)){
              pagamentoEmAndamento.rejeitaPagamento();
        }
        pagamentoRepositoryPort.salvaStatus(pagamentoEmAndamento);
    }

    private boolean podeSerProcessado(StatusPagamento statusAtual, StatusPagamento statusRequest) {
        return statusAtual.equals(StatusPagamento.PENDING) && statusRequest.equals(StatusPagamento.IN_PROCESS);
    }

    private boolean podeSerAprovado(StatusPagamento statusAtual, StatusPagamento statusRequest) {
        return statusAtual.equals(StatusPagamento.IN_PROCESS) && statusRequest.equals(StatusPagamento.APPROVED);
    }

    private boolean podeSerCancelado(StatusPagamento statusAtual, StatusPagamento statusRequest) {
        return statusAtual.equals(StatusPagamento.IN_PROCESS) && statusRequest.equals(StatusPagamento.CANCELLED);
    }

    private boolean podeSerRejeitado(StatusPagamento statusAtual, StatusPagamento statusRequest) {
        return statusAtual.equals(StatusPagamento.IN_PROCESS) && statusRequest.equals(StatusPagamento.REJECTED);
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
        if(podeSerProcessado(pagamento.getStatus(), statusRequest)){
            pagamento.colocaEmProcessamento();
            pagamentoRepositoryPort.salvaStatus(pagamento);
        }
        //analisa ql é o status retornado pelo gateway e ajusta o proximo status
        lidaStatusPagamento(pagamento.getStatus(), new PagamentoDTO(pagamento), statusRequest);
    }

    /**
     * Simula a lógica da Domain Service usando o gateway
     * Para sua implementação é necessária a instalação do SDK do MP
     * o payload é definido de forma específica para o Gateway
     */
    @Override
    public void enviaGatewayDePagamento(CompraAPagarDTO compraAPagarDTO) {
        System.out.println("verficando qual Gateway será usado...");
        System.out.println("Criando payload de acordo com o Gateway do Mercado Pago");
        System.out.println("Enviando ao Mercado Pago a request de pagamento...");
        System.out.println("Criando código para Pagamento do pedido: " + compraAPagarDTO.getCodigoPedido());
        //só vai funcionar quando a integração estiver ok, os pedidos precisam ser recuperados do domínio de Pedidos
        geraPagamentoDoPedido(compraAPagarDTO);
        System.out.println("Aguardando retorno com o status do pagamento");
    }

    private void geraPagamentoDoPedido(CompraAPagarDTO compraAPagarDTO) {
        Pagamento novoPagamento = new Pagamento((criaPagamento(findByCodigoPedido(compraAPagarDTO.getCodigoPedido()))));
        pagamentoRepositoryPort.salvaPagamento(novoPagamento);
        new PagamentoDTO(novoPagamento);
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

    private boolean verificaTransacaoPagamentoEmAndamento(PagamentoDTO pagamentoDTO) {
        return (findByCodigoPedido(pagamentoDTO.getCodigoPedido()) != null && pagamentoDTO.getStatus().equals(StatusPagamento.PENDING));
    }

    private boolean podeIniciarPagamento(PagamentoDTO pagamentoDTO) {
       return pagamentoDTO.getStatus().equals(StatusPagamento.PENDING);
    }

    private void iniciaStatusPagamento(PagamentoDTO pagamentoDTO){
        pagamentoDTO.setStatus(StatusPagamento.PENDING);
    }

}
