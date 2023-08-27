package br.fiap.projeto.contexto.pagamento.usecase.port.repository;

import br.fiap.projeto.contexto.pagamento.entity.Pagamento;

public interface IProcessaNovoPagamentoRepositoryAdapterGateway {

    void salvaNovoPagamento(Pagamento pagamento);

    void verificaCondicoesParaCriarPagamento(Pagamento pagamento);

    void verificaSeJaExistePagamentoParaOPedido(Pagamento pagamento);
}
