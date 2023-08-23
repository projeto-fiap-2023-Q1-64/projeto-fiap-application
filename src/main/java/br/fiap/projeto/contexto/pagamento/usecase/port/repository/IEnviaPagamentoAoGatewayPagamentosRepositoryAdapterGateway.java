package br.fiap.projeto.contexto.pagamento.usecase.port.repository;

import br.fiap.projeto.contexto.pagamento.entity.Pagamento;

public interface IEnviaPagamentoAoGatewayPagamentosRepositoryAdapterGateway {
    void envia(Pagamento pagamento);

    //TODO validar necessidade dentro do fluxo do envio ao MP
    Pagamento preparaRequest(Pagamento pagamento);
}
