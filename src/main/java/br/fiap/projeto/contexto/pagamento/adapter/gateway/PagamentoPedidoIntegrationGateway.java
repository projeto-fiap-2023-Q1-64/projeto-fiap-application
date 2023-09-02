package br.fiap.projeto.contexto.pagamento.adapter.gateway;

import br.fiap.projeto.contexto.pagamento.adapter.mapper.PagamentoPedidoMapper;
import br.fiap.projeto.contexto.pagamento.entity.integration.PagamentoPedido;
import br.fiap.projeto.contexto.pagamento.external.integration.IPedidoIntegration;
import br.fiap.projeto.contexto.pagamento.external.integration.port.Pedido;
import br.fiap.projeto.contexto.pagamento.usecase.port.repository.IPagamentoPedidoIntegrationGateway;

import java.util.List;
import java.util.stream.Collectors;

public class PagamentoPedidoIntegrationGateway implements IPagamentoPedidoIntegrationGateway {
    private final IPedidoIntegration pedidoIntegration;
    public PagamentoPedidoIntegrationGateway(IPedidoIntegration pedidoIntegration) {
        this.pedidoIntegration = pedidoIntegration;
    }

    @Override
    public List<PagamentoPedido> buscaPedidosAPagar() {
        return pedidoIntegration.buscaPedidosAPagar().stream().map(PagamentoPedidoMapper::toPagamentoPedido).collect(Collectors.toList());
    }
}