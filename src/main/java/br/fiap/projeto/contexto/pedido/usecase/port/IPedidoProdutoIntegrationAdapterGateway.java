package br.fiap.projeto.contexto.pedido.usecase.port;

import br.fiap.projeto.contexto.pedido.entity.integration.ProdutoPedido;

import java.util.UUID;

public interface IPedidoProdutoIntegrationAdapterGateway {
    ProdutoPedido getProduto(UUID codigoProduto);
}
