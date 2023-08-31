package br.fiap.projeto.contexto.pagamento.adapter.controller;

import br.fiap.projeto.contexto.pagamento.adapter.controller.rest.port.IAtualizaPagamentoRestAdapterController;
import br.fiap.projeto.contexto.pagamento.adapter.controller.rest.request.PagamentoDTORequest;
import br.fiap.projeto.contexto.pagamento.adapter.controller.rest.response.PagamentoDTOResponse;
import br.fiap.projeto.contexto.pagamento.entity.Pagamento;
import br.fiap.projeto.contexto.pagamento.entity.enums.StatusPagamento;
import br.fiap.projeto.contexto.pagamento.usecase.exceptions.ResourceNotFoundException;
import br.fiap.projeto.contexto.pagamento.usecase.port.usecase.IAtualizaStatusPagamentoUsecase;
import br.fiap.projeto.contexto.pagamento.usecase.port.usecase.IBuscaPagamentoUseCase;

import java.util.List;
import java.util.Optional;

public class AtualizaStatusPagamentoRestAdapterController implements IAtualizaPagamentoRestAdapterController {

    private final IAtualizaStatusPagamentoUsecase atualizaStatusPagamentoUsecase;
    private final IBuscaPagamentoUseCase buscaPagamentoUseCase;

    public AtualizaStatusPagamentoRestAdapterController(IAtualizaStatusPagamentoUsecase atualizaStatusPagamentoUsecase, IBuscaPagamentoUseCase buscaPagamentoUseCase) {
        this.atualizaStatusPagamentoUsecase = atualizaStatusPagamentoUsecase;
        this.buscaPagamentoUseCase = buscaPagamentoUseCase;
    }

    /**
     * Atualização de Status de Pagamento:<br>
     * <li>Pagamento PENDENTE poderá ser colocado EM PROCESSAMENTO</li>
     * <li>Pagamento EM PROCESSAMENTO poderá ser colocado em: APROVADO, REJEITADO</li>
     * <li>Pagamento REJEITADO poderá ser colocado em: CANCELADO</li>
     * @param pagamentoDTORequest
     */
    @Override
    public void atualizaStatusPagamento(PagamentoDTORequest pagamentoDTORequest) {
        PagamentoDTOResponse pagamentoDTOStatusAtual;
        if(pagamentoDTORequest.getStatus().equals(StatusPagamento.CANCELLED)){
            System.out.println("Pedido a ser cancelado...");
            pagamentoDTOStatusAtual = new PagamentoDTOResponse(getByCodigoPedidoParaCancelarPagamento(pagamentoDTORequest));
        }else{
            System.out.println("Pedido a ser atualizado...");
            pagamentoDTOStatusAtual = new PagamentoDTOResponse(getByCodigoPedido(pagamentoDTORequest));
        }
        efetuaProcessamentoDaAtualizacaoDeStatusDePagamento(pagamentoDTORequest, pagamentoDTOStatusAtual);
    }

    private void efetuaProcessamentoDaAtualizacaoDeStatusDePagamento(PagamentoDTORequest pagamentoDTORequest, PagamentoDTOResponse pagamentoDTOStatusAtual) {
        atualizaStatusPagamentoUsecase.analisaStatusDoPagamento(
                pagamentoDTOStatusAtual.getStatus(),
                pagamentoDTORequest.getStatus(),
                pagamentoDTOStatusAtual.conversorDePagamentoDTOResponseParaPagamento()
        );

        //INFO aqui o pagamentoDTORequest precisa receber os outros dados do pagamento pois chegará sem
        pagamentoDTORequest.atualizaDadosRequest(pagamentoDTORequest, pagamentoDTOStatusAtual);
        atualizaStatusPagamentoUsecase.salvaStatus(pagamentoDTORequest.conversorDePagamentoDTORequestParaPagamento());
    }

    //TODO verificar uma forma mais simplificada de fazer os dois getByCodigoPedido -> unica diff é o filter
    private Pagamento getByCodigoPedidoParaCancelarPagamento(PagamentoDTORequest pagamentoDTORequest) {
        List<Pagamento> listaDePagamentosPorCodigoPedido = buscaPagamentoUseCase.findByCodigoPedido(pagamentoDTORequest.getCodigoPedido());
        Optional<Pagamento> possivelPagamento = listaDePagamentosPorCodigoPedido
                .stream()
                .filter(pagamento -> pagamentoDTORequest.getCodigoPedido().equals(pagamento.getCodigoPedido())
                        && StatusPagamento.REJECTED.equals(pagamento.getStatus()))
                .findFirst();
        return possivelPagamento.orElseThrow( () -> new ResourceNotFoundException("Pagamento não encontrado.") );
    }

    private Pagamento getByCodigoPedido(PagamentoDTORequest pagamentoDTORequest){
        List<Pagamento> listaDePagamentosPorCodigoPedido = buscaPagamentoUseCase.findByCodigoPedido(pagamentoDTORequest.getCodigoPedido());
        Optional<Pagamento> possivelPagamento = listaDePagamentosPorCodigoPedido
                                    .stream()
                                    .filter(pagamento -> pagamentoDTORequest.getCodigoPedido().equals(pagamento.getCodigoPedido())
                                            && !StatusPagamento.REJECTED.equals(pagamento.getStatus()))
                                    .findFirst();
        return possivelPagamento.orElseThrow( () -> new ResourceNotFoundException("Pagamento não encontrado.") );
    }
}
