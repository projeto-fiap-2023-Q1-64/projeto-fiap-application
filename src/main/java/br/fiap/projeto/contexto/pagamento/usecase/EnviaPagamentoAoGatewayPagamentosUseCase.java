package br.fiap.projeto.contexto.pagamento.usecase;

import br.fiap.projeto.contexto.pagamento.entity.Pagamento;
import br.fiap.projeto.contexto.pagamento.usecase.port.repository.IEnviaPagamentoAoGatewayPagamentosRepositoryAdapterGateway;
import br.fiap.projeto.contexto.pagamento.usecase.port.usecase.IEnviaPagamentoAoGatewayPagamentosUseCase;

public class EnviaPagamentoAoGatewayPagamentosUseCase implements IEnviaPagamentoAoGatewayPagamentosUseCase {

    private final IEnviaPagamentoAoGatewayPagamentosRepositoryAdapterGateway enviaPagamentoAoGatewayPagamentosAdapterGateway;

    public EnviaPagamentoAoGatewayPagamentosUseCase(IEnviaPagamentoAoGatewayPagamentosRepositoryAdapterGateway enviaPagamentoAoGatewayPagamentosAdapterGateway) {
        this.enviaPagamentoAoGatewayPagamentosAdapterGateway = enviaPagamentoAoGatewayPagamentosAdapterGateway;
    }

    //TODO a interface que será usada para enviar deve ser uma que chama o MP
   @Override
    public void enviaRequestAoSistemaExternoPagamentos(Pagamento pagamento) {
        //TODO verificar a chamada ao repository
        enviaPagamentoAoGatewayPagamentosAdapterGateway.envia(pagamento);
    }
}
