package br.fiap.projeto.contexto.pedido.adapter.controller.port;

import br.fiap.projeto.contexto.pedido.adapter.controller.rest.request.PedidoCriarDTO;
import br.fiap.projeto.contexto.pedido.adapter.controller.rest.request.ProdutoPedidoDTO;
import br.fiap.projeto.contexto.pedido.adapter.controller.rest.response.PedidoDTO;

import java.util.List;
import java.util.UUID;

public interface IPedidoManagementRestAdapterController {
    PedidoDTO criaPedido(PedidoCriarDTO pedidoCriarDTO);
    PedidoDTO adicionarProduto(UUID codigoPedido, ProdutoPedidoDTO produtoPedidoDTO) throws Exception;
    void removerProduto(UUID codigoPedido, UUID produtoCodigo) throws Exception;
    PedidoDTO aumentarQuantidade(UUID codigoPedido, UUID produtoCodigo) throws Exception;
    PedidoDTO reduzirQuantidade(UUID codigoPedido, UUID produtoCodigo) throws Exception;
}