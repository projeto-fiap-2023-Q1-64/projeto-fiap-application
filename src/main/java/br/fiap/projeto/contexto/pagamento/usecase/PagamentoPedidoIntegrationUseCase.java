package br.fiap.projeto.contexto.pagamento.usecase;

import br.fiap.projeto.contexto.pagamento.entity.Pagamento;
import br.fiap.projeto.contexto.pagamento.entity.integration.PagamentoPedidoResponse;
import br.fiap.projeto.contexto.pagamento.external.integration.IPagamentoPedidoIntegration;
import br.fiap.projeto.contexto.pagamento.external.integration.port.PagamentoPedidoResponseDTO;
import br.fiap.projeto.contexto.pagamento.external.integration.port.Pedido;
import br.fiap.projeto.contexto.pagamento.usecase.port.repository.IPagamentoPedidoIntegrationGateway;
import br.fiap.projeto.contexto.pagamento.usecase.port.usecase.IPagamentoPedidoIntegrationUseCase;

import java.util.UUID;

public class PagamentoPedidoIntegrationUseCase implements IPagamentoPedidoIntegrationUseCase {

    private final IPagamentoPedidoIntegrationGateway pagamentoPedidoIntegrationGateway;

    public PagamentoPedidoIntegrationUseCase(IPagamentoPedidoIntegrationGateway pagamentoPedidoIntegrationGateway) {
        this.pagamentoPedidoIntegrationGateway = pagamentoPedidoIntegrationGateway;
    }

   @Override
    public void atualizarPagamentoPedido(Pagamento pagamento) {
       pagamentoPedidoIntegrationGateway.atualizaStatusPagamentoPedido(
               new PagamentoPedidoResponse(pagamento.getCodigoPedido(), pagamento.getStatus().name()));
    }
}
