package br.fiap.projeto.contexto.pedido.adapter.gateway;

import br.fiap.projeto.contexto.pedido.adapter.mapper.ProdutoMapper;
import br.fiap.projeto.contexto.pedido.entity.integration.ProdutoPedido;
import br.fiap.projeto.contexto.pedido.external.integration.PedidoProdutoIntegration;
import br.fiap.projeto.contexto.pedido.usecase.port.adaptergateway.IPedidoProdutoIntegrationAdapterGateway;

import java.util.UUID;

public class PedidoProdutoIntegrationAdapterGateway implements IPedidoProdutoIntegrationAdapterGateway {
    private final PedidoProdutoIntegration pedidoProdutoIntegration;

    public PedidoProdutoIntegrationAdapterGateway(PedidoProdutoIntegration pedidoProdutoIntegration) {
        this.pedidoProdutoIntegration = pedidoProdutoIntegration;
    }
    @Override
    public ProdutoPedido getProduto(UUID codigoProduto) {
        return ProdutoMapper.toProdutoPedido(pedidoProdutoIntegration.getProduto(codigoProduto));
    }
}
