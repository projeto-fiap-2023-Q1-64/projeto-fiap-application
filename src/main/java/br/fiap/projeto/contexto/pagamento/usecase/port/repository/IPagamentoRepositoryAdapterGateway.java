package br.fiap.projeto.contexto.pagamento.usecase.port.repository;

import br.fiap.projeto.contexto.pagamento.entity.Pagamento;

public interface IPagamentoRepositoryAdapterGateway {

    //TODO Refatorar após testes - usado apenas no DataLoader
     void salvaPagamento(Pagamento pagamento);




}
