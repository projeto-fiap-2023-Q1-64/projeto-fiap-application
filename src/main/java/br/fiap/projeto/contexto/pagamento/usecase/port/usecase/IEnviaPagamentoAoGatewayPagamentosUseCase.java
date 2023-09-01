package br.fiap.projeto.contexto.pagamento.usecase.port.usecase;

import br.fiap.projeto.contexto.pagamento.entity.Pagamento;
import br.fiap.projeto.contexto.pagamento.entity.enums.StatusPagamento;

public interface IEnviaPagamentoAoGatewayPagamentosUseCase {

    void enviaRequestAoSistemaExternoPagamentos(String codigoPedido, StatusPagamento status);

    void verificaPagamentoAntesDeEnviarAoGateway(String codigoPedido, StatusPagamento status);

    void validaStatusAtualDoPagamentoAntesDeEnviarAoGateway(String codigoPedido, StatusPagamento status);

    Pagamento atualizaStatusNovoAoEnviarPagamentoAoGateway(String codigoPedido, StatusPagamento statusPagamento);
}
