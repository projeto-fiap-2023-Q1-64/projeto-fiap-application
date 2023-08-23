package br.fiap.projeto.contexto.pagamento.usecase.port.usecase;


import br.fiap.projeto.contexto.pagamento.entity.Pagamento;

public interface IProcessaNovoPagamentoUseCase {

    void criaNovoPagamento(Pagamento pagamento);//recebe pedido a pagar

    void verificaSeJaExistePagamentoParaOPedido(Pagamento pagamento);

    void verificaCondicoesParaCriarPagamento(Pagamento pagamento);
}
