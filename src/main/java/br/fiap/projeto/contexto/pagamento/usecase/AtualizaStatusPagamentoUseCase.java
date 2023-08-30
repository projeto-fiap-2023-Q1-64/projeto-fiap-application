package br.fiap.projeto.contexto.pagamento.usecase;

import br.fiap.projeto.contexto.pagamento.adapter.controller.rest.response.PagamentoDTOResponse;
import br.fiap.projeto.contexto.pagamento.entity.Pagamento;
import br.fiap.projeto.contexto.pagamento.entity.enums.StatusPagamento;
import br.fiap.projeto.contexto.pagamento.usecase.port.repository.IAtualizaStatusPagamentoRepositoryAdapterGateway;
import br.fiap.projeto.contexto.pagamento.usecase.port.usecase.IAtualizaStatusPagamentoUsecase;

public class AtualizaStatusPagamentoUseCase implements IAtualizaStatusPagamentoUsecase {

    private final IAtualizaStatusPagamentoRepositoryAdapterGateway atualizaStatusPagamentoAdapterGateway;

    public AtualizaStatusPagamentoUseCase(IAtualizaStatusPagamentoRepositoryAdapterGateway atualizaStatusPagamentoAdapterGateway) {
        this.atualizaStatusPagamentoAdapterGateway = atualizaStatusPagamentoAdapterGateway;
    }

    @Override
    public void analisaStatusDoPagamento(StatusPagamento statusAtual, StatusPagamento statusRequest, Pagamento pagamentoEmAndamento) {
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
        //TODO verificar esse save - pode estar duplicando
        //atualizaStatusPagamentoAdapterGateway.atualizaStatusPagamento(pagamentoEmAndamento);
    }

    @Override
    public void salvaStatus(Pagamento pagamento) {
        atualizaStatusPagamentoAdapterGateway.atualizaStatusPagamento(pagamento);
    }

}
