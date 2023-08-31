package br.fiap.projeto.contexto.pedido.adapter.gateway;

import br.fiap.projeto.contexto.pedido.adapter.mapper.PagamentoMapper;
import br.fiap.projeto.contexto.pedido.entity.integration.PagamentoPedido;
import br.fiap.projeto.contexto.pedido.external.integration.PedidoPagamentoIntegration;
import br.fiap.projeto.contexto.pedido.usecase.port.adaptergateway.IPedidoPagamentoIntegrationAdapterGateway;

import java.util.UUID;

public class PedidoPagamentoIntegrationAdapterGateway implements IPedidoPagamentoIntegrationAdapterGateway {
    final PedidoPagamentoIntegration pedidoPagamentoIntegration;

    public PedidoPagamentoIntegrationAdapterGateway(PedidoPagamentoIntegration pedidoPagamentoIntegration) {
        this.pedidoPagamentoIntegration = pedidoPagamentoIntegration;
    }

    @Override
    public PagamentoPedido buscaStatusPagamentoPorCodigoPedido(UUID codigoPedido) {
        return PagamentoMapper.toPagamentoPedido(pedidoPagamentoIntegration.buscaStatusPagamentoPorCodigoPedido(codigoPedido.toString()));
    }
}
