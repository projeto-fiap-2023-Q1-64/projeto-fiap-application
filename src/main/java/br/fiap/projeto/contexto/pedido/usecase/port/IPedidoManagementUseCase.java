package br.fiap.projeto.contexto.pedido.usecase.port;

import br.fiap.projeto.contexto.pedido.adapter.controller.rest.request.PedidoCriarDTO;
import br.fiap.projeto.contexto.pedido.adapter.controller.rest.response.ItemPedidoDTO;
import br.fiap.projeto.contexto.pedido.adapter.controller.rest.response.PedidoDTO;
import br.fiap.projeto.contexto.pedido.adapter.controller.rest.request.ProdutoPedidoDTO;
import br.fiap.projeto.contexto.pedido.usecase.exception.InvalidOperacaoProdutoException;
import br.fiap.projeto.contexto.pedido.usecase.exception.ItemNotFoundException;

import java.util.List;
import java.util.UUID;

public interface IPedidoManagementUseCase {
    PedidoDTO criaPedido(PedidoCriarDTO pedidoDTO);
    PedidoDTO adicionarProduto(UUID codigoPedido, ProdutoPedidoDTO produtoDTO) throws InvalidOperacaoProdutoException;
    PedidoDTO aumentarQuantidade(UUID codigoPedido, UUID codigoProduto) throws ItemNotFoundException, InvalidOperacaoProdutoException;
    PedidoDTO reduzirQuantidade(UUID codigoPedido, UUID codigoProduto) throws ItemNotFoundException, InvalidOperacaoProdutoException;
    void removerProduto(UUID codigoPedido, UUID codigoProduto) throws InvalidOperacaoProdutoException;
}
