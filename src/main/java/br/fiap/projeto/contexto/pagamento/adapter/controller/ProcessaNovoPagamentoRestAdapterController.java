package br.fiap.projeto.contexto.pagamento.adapter.controller;

import br.fiap.projeto.contexto.pagamento.adapter.controller.rest.port.IProcessaPagamentoRestAdapterController;
import br.fiap.projeto.contexto.pagamento.adapter.controller.rest.request.PagamentoDTORequest;
import br.fiap.projeto.contexto.pagamento.adapter.controller.rest.response.PagamentoDTOResponse;
import br.fiap.projeto.contexto.pagamento.entity.Pagamento;
import br.fiap.projeto.contexto.pagamento.usecase.port.usecase.IAtualizaStatusPagamentoUsecase;
import br.fiap.projeto.contexto.pagamento.usecase.port.usecase.IBuscaPagamentoUseCase;
import br.fiap.projeto.contexto.pagamento.usecase.port.usecase.IProcessaNovoPagamentoUseCase;

public class ProcessaNovoPagamentoRestAdapterController implements IProcessaPagamentoRestAdapterController {

    private final IProcessaNovoPagamentoUseCase processaNovoPagamentoUseCase;

    //TODO remover o atualizaStatus
    private final IAtualizaStatusPagamentoUsecase atualizaStatusPagamentoUsecase;

    private final IBuscaPagamentoUseCase buscaPagamentoUseCase;
    public ProcessaNovoPagamentoRestAdapterController(IProcessaNovoPagamentoUseCase processaNovoPagamentoUseCase, IAtualizaStatusPagamentoUsecase atualizaStatusPagamentoUsecase, IBuscaPagamentoUseCase buscaPagamentoUseCase) {
        this.processaNovoPagamentoUseCase = processaNovoPagamentoUseCase;
        this.atualizaStatusPagamentoUsecase = atualizaStatusPagamentoUsecase;
        this.buscaPagamentoUseCase = buscaPagamentoUseCase;
    }

    @Override
    public PagamentoDTOResponse criaNovoPagamento(PagamentoDTORequest pagamentoDTORequest) {
        processaNovoPagamentoUseCase.verificaCondicoesParaCriarPagamento(getByCodigo(pagamentoDTORequest));
        processaNovoPagamentoUseCase.criaNovoPagamento(new Pagamento(pagamentoDTORequest));
        return new PagamentoDTOResponse(getByCodigo(pagamentoDTORequest));
    }

    private Pagamento getByCodigo(PagamentoDTORequest pagamentoDTORequest) {
        return buscaPagamentoUseCase.findByCodigo(pagamentoDTORequest.getCodigo());
    }

}
