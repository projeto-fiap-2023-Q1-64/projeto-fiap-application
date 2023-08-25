package br.fiap.projeto.contexto.pedido.adapter.gateway;

import br.fiap.projeto.contexto.pedido.external.integration.PedidoClienteIntegration;
import br.fiap.projeto.contexto.pedido.external.integration.port.Cliente;
import br.fiap.projeto.contexto.pedido.usecase.port.IPedidoClienteIntegrationAdapterGateway;

import java.util.UUID;

public class PedidoClienteIntegrationAdapterGateway implements IPedidoClienteIntegrationAdapterGateway {
    private final PedidoClienteIntegration pedidoClienteIntegration;

    public PedidoClienteIntegrationAdapterGateway(PedidoClienteIntegration pedidoClienteIntegration) {
        this.pedidoClienteIntegration = pedidoClienteIntegration;
    }
    @Override
    public Boolean VerificaClienteExite(UUID codigoCliente) {
        Cliente cliente = pedidoClienteIntegration.busca(codigoCliente.toString());
        return cliente != null && !cliente.getCodigo().isEmpty();
    }
}