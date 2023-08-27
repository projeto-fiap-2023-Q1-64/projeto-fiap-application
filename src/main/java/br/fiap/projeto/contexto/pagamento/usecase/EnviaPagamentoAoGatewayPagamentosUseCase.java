package br.fiap.projeto.contexto.pagamento.usecase;

import br.fiap.projeto.contexto.pagamento.entity.Pagamento;
import br.fiap.projeto.contexto.pagamento.usecase.port.repository.IEnviaPagamentoAoGatewayPagamentosRepositoryAdapterGateway;
import br.fiap.projeto.contexto.pagamento.usecase.port.usecase.IEnviaPagamentoAoGatewayPagamentosUseCase;

public class EnviaPagamentoAoGatewayPagamentosUseCase implements IEnviaPagamentoAoGatewayPagamentosUseCase {

    private final IEnviaPagamentoAoGatewayPagamentosRepositoryAdapterGateway enviaPagamentoAoGatewayPagamentosAdapterGateway;

    public EnviaPagamentoAoGatewayPagamentosUseCase(IEnviaPagamentoAoGatewayPagamentosRepositoryAdapterGateway enviaPagamentoAoGatewayPagamentosAdapterGateway) {
        this.enviaPagamentoAoGatewayPagamentosAdapterGateway = enviaPagamentoAoGatewayPagamentosAdapterGateway;
    }

    //TODO injetar a dependência que será usada para enviar deve ser uma que chama o MP
   @Override
    public void enviaRequestAoSistemaExternoPagamentos(Pagamento pagamento) {
        //TODO aqui faria chamadas às integrações com o sistema externo - hoje apenas é simulado
       System.out.println("Enviando pagamento ao Sistema Externo de Pagamentos: MercadoPago");
       enviaPagamentoAoGatewayPagamentosAdapterGateway.persisteInfoDoPagamentoEnviadoAoGateway(pagamento);
    }
}
