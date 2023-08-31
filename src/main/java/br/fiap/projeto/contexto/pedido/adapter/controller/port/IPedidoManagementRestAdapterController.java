package br.fiap.projeto.contexto.pedido.adapter.controller.port;

import br.fiap.projeto.contexto.pedido.adapter.controller.rest.response.PedidoDTO;

import java.util.UUID;

public interface IPedidoManagementRestAdapterController {
    PedidoDTO criaPedido(String codigoCliente);
    PedidoDTO adicionarProduto(UUID codigoPedido, UUID codigoProduto) throws Exception;
    void removerProduto(UUID codigoPedido, UUID produtoCodigo) throws Exception;
    PedidoDTO aumentarQuantidade(UUID codigoPedido, UUID produtoCodigo) throws Exception;
    PedidoDTO reduzirQuantidade(UUID codigoPedido, UUID produtoCodigo) throws Exception;

}