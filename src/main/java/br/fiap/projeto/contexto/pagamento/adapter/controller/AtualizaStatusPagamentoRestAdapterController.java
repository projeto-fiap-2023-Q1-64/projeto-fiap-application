package br.fiap.projeto.contexto.pagamento.adapter.controller;

import br.fiap.projeto.contexto.pagamento.adapter.controller.rest.port.IAtualizaPagamentoRestAdapterController;
import br.fiap.projeto.contexto.pagamento.adapter.controller.rest.request.PagamentoDTORequest;
import br.fiap.projeto.contexto.pagamento.adapter.controller.rest.response.PagamentoDTOResponse;
import br.fiap.projeto.contexto.pagamento.entity.Pagamento;
import br.fiap.projeto.contexto.pagamento.usecase.port.usecase.IAtualizaStatusPagamentoUsecase;
import br.fiap.projeto.contexto.pagamento.usecase.port.usecase.IBuscaPagamentoUseCase;

public class AtualizaStatusPagamentoRestAdapterController implements IAtualizaPagamentoRestAdapterController {

    private final IAtualizaStatusPagamentoUsecase atualizaStatusPagamentoUsecase;
    private final IBuscaPagamentoUseCase buscaPagamentoUseCase;

    public AtualizaStatusPagamentoRestAdapterController(IAtualizaStatusPagamentoUsecase atualizaStatusPagamentoUsecase, IBuscaPagamentoUseCase buscaPagamentoUseCase) {
        this.atualizaStatusPagamentoUsecase = atualizaStatusPagamentoUsecase;
        this.buscaPagamentoUseCase = buscaPagamentoUseCase;
    }

    @Override
    public void atualizaStatusPagamento(PagamentoDTORequest pagamentoDTORequest) {
        PagamentoDTOResponse pagamentoDTOStatusAtual = new PagamentoDTOResponse(getByCodigoPedido(pagamentoDTORequest));
        atualizaStatusPagamentoUsecase.analisaStatusDoPagamento(
                pagamentoDTOStatusAtual.getStatus(),
                pagamentoDTORequest.getStatus(),
                pagamentoDTOStatusAtual.conversorDePagamentoDTOResponseParaPagamento()
        );
        //INFO aqui o pagamentoDTORequest precisa receber os outros dados do pagamento pois chegará sem
        pagamentoDTORequest.atualizaDadosRequest(pagamentoDTORequest, pagamentoDTOStatusAtual);
        atualizaStatusPagamentoUsecase.salvaStatus(pagamentoDTORequest.conversorDePagamentoDTORequestParaPagamento());
    }

    private Pagamento getByCodigoPedido(PagamentoDTORequest pagamentoDTORequest){
        return buscaPagamentoUseCase.findByCodigoPedido(pagamentoDTORequest.getCodigoPedido());
    }
}
