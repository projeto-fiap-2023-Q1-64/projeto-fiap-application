package br.fiap.projeto.contexto.pagamento.usecase;

import br.fiap.projeto.contexto.pagamento.entity.Pagamento;
import br.fiap.projeto.contexto.pagamento.entity.enums.StatusPagamento;
import br.fiap.projeto.contexto.pagamento.usecase.exceptions.UnprocessablePaymentException;
import br.fiap.projeto.contexto.pagamento.usecase.port.repository.IAtualizaStatusPagamentoRepositoryAdapterGateway;
import br.fiap.projeto.contexto.pagamento.usecase.port.usecase.IAtualizaStatusPagamentoUsecase;
import br.fiap.projeto.contexto.pagamento.usecase.port.usecase.IBuscaPagamentoUseCase;

import java.util.UUID;

public class AtualizaStatusPagamentoUseCase implements IAtualizaStatusPagamentoUsecase {

    private final IAtualizaStatusPagamentoRepositoryAdapterGateway atualizaStatusPagamentoAdapterGateway;
    private final IBuscaPagamentoUseCase buscaPagamentoUseCase;

    public AtualizaStatusPagamentoUseCase(IAtualizaStatusPagamentoRepositoryAdapterGateway atualizaStatusPagamentoAdapterGateway,
                                          IBuscaPagamentoUseCase buscaPagamentoUseCase) {
        this.atualizaStatusPagamentoAdapterGateway = atualizaStatusPagamentoAdapterGateway;
        this.buscaPagamentoUseCase = buscaPagamentoUseCase;
    }

    @Override
    public void analisaStatusDoPagamento(StatusPagamento statusAtual, StatusPagamento statusRequest, Pagamento pagamentoEmAndamento) {

        validaCondicoesDeStatusEnviadasNaRequest(statusAtual, statusRequest, pagamentoEmAndamento);

        if(pagamentoEmAndamento.podeSerProcessado(statusAtual, statusRequest)){
            System.out.println("Pode ser processado ");
            pagamentoEmAndamento.colocaEmProcessamento(pagamentoEmAndamento);
        }
        if(pagamentoEmAndamento.podeSerAprovado(statusAtual, statusRequest)) {
            System.out.println("Pode ser aprovado ");
            pagamentoEmAndamento.aprovaPagamento(pagamentoEmAndamento);
        }
        if(pagamentoEmAndamento.podeSerCancelado(statusAtual, statusRequest)){
            System.out.println("Pode ser cancelado ");
            pagamentoEmAndamento.cancelaPagamento(pagamentoEmAndamento);
        }
        if(pagamentoEmAndamento.podeSerRejeitado(statusAtual, statusRequest)){
            System.out.println("Pode ser rejeitado ");
            pagamentoEmAndamento.rejeitaPagamento(pagamentoEmAndamento);
        }

    }

    private static void validaCondicoesDeStatusEnviadasNaRequest(StatusPagamento statusAtual, StatusPagamento statusRequest, Pagamento pagamentoEmAndamento) {
        if( !pagamentoEmAndamento.podeSerProcessado(statusAtual, statusRequest) &&
            !pagamentoEmAndamento.podeSerAprovado(statusAtual, statusRequest)   &&
            !pagamentoEmAndamento.podeSerCancelado(statusAtual, statusRequest)  &&
            !pagamentoEmAndamento.podeSerRejeitado(statusAtual, statusRequest)  ){
            throw new UnprocessablePaymentException("Mudança de Status de Pagamento inválida.");
        }
    }

    @Override
    public void salvaStatus(Pagamento pagamento) {
        atualizaStatusPagamentoAdapterGateway.atualizaStatusPagamento(pagamento);
    }

    @Override
    public void atualizaStatusPagamento(String codigoPedido, StatusPagamento statusPagamento) {
        Pagamento pagamento;
        if(statusPagamento.equals(StatusPagamento.CANCELLED)){
            pagamento = buscaPagamentoUseCase.findByCodigoPedidoRejected(codigoPedido);
        }else{
            pagamento = buscaPagamentoUseCase.findByCodigoPedidoNotRejected(codigoPedido);
        }
        analisaStatusDoPagamento(pagamento.getStatus(),
                statusPagamento,
                pagamento);

        salvaStatus(pagamento);
    }
}