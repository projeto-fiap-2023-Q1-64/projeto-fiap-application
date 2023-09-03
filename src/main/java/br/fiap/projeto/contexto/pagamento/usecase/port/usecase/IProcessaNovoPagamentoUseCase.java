package br.fiap.projeto.contexto.pagamento.usecase.port.usecase;


import br.fiap.projeto.contexto.pagamento.entity.Pagamento;

public interface IProcessaNovoPagamentoUseCase {

    //INFO cria pagamentos para os pedidos recebidos no endpoint de integração
    Pagamento criaNovoPagamento(Pagamento pagamento);
}
