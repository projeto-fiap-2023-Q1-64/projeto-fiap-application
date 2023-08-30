package br.fiap.projeto.contexto.pagamento.usecase.port.usecase;

import br.fiap.projeto.contexto.pagamento.entity.Pagamento;
import br.fiap.projeto.contexto.pagamento.entity.enums.StatusPagamento;

public interface IAtualizaStatusPagamentoUsecase {
    void analisaStatusDoPagamento(StatusPagamento statusAtual, StatusPagamento statusRequest, Pagamento pagamentoEmAndamento);

    void salvaStatus(Pagamento pagamento);

}
