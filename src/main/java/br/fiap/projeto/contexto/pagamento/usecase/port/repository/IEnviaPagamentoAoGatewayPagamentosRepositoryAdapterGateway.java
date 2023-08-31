package br.fiap.projeto.contexto.pagamento.usecase.port.repository;

import br.fiap.projeto.contexto.pagamento.entity.Pagamento;

import java.util.List;

public interface IEnviaPagamentoAoGatewayPagamentosRepositoryAdapterGateway {
    void persisteInfoDoPagamentoEnviadoAoGateway(Pagamento pagamento);

    //TODO validar necessidade dentro do fluxo do envio ao MP
    List<Pagamento> preparaRequest(Pagamento pagamento);
}
