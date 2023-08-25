package br.fiap.projeto.contexto.pedido.adapter.gateway;

import br.fiap.projeto.contexto.pedido.entity.enums.CategoriaProduto;
import br.fiap.projeto.contexto.pedido.entity.integration.ProdutoPedido;
import br.fiap.projeto.contexto.pedido.external.integration.PedidoProdutoIntegration;
import br.fiap.projeto.contexto.pedido.external.integration.port.Produto;
import br.fiap.projeto.contexto.pedido.usecase.port.IPedidoProdutoIntegrationAdapterGateway;

import java.util.UUID;

public class PedidoProdutoIntegrationAdapterGateway implements IPedidoProdutoIntegrationAdapterGateway {
    private final PedidoProdutoIntegration pedidoProdutoIntegration;

    public PedidoProdutoIntegrationAdapterGateway(PedidoProdutoIntegration pedidoProdutoIntegration) {
        this.pedidoProdutoIntegration = pedidoProdutoIntegration;
    }
    @Override
    public ProdutoPedido getProduto(UUID codigoProduto) {
        Produto produto = pedidoProdutoIntegration.getProduto(codigoProduto);
        return new ProdutoPedido( UUID.fromString(produto.getCodigo()),
                produto.getNome(),
                produto.getDescricao(),
                produto.getPreco(),
                CategoriaProduto.valueOf(produto.getCategoria()),
                produto.getImagem(),
                produto.getTempoPreparoMin());
    }
}
