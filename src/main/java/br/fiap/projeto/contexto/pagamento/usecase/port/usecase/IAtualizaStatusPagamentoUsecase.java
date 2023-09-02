package br.fiap.projeto.contexto.pagamento.usecase.port.usecase;

import br.fiap.projeto.contexto.pagamento.entity.enums.StatusPagamento;

public interface IAtualizaStatusPagamentoUsecase {
    //TODO verificar a necessidade de devolver o pagamento atualizado na ResponseEntity
    void atualizaStatusPagamento(String codigoPedido, StatusPagamento statusPagamento);

    void atualizaStatusPagamentoGateway(String codigoPedido, StatusPagamento statusPagamento);
}
