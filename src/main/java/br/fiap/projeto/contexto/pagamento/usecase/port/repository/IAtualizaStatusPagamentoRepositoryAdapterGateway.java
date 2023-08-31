package br.fiap.projeto.contexto.pagamento.usecase.port.repository;


import br.fiap.projeto.contexto.pagamento.entity.Pagamento;



public interface IAtualizaStatusPagamentoRepositoryAdapterGateway {

    void atualizaStatusPagamento(Pagamento pagamento);

}
