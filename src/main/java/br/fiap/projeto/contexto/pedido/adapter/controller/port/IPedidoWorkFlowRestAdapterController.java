package br.fiap.projeto.contexto.pedido.adapter.controller.port;

import br.fiap.projeto.contexto.pedido.adapter.controller.rest.response.PedidoDTO;
import br.fiap.projeto.contexto.pedido.entity.Pedido;

import java.util.UUID;

public interface IPedidoWorkFlowRestAdapterController {
    PedidoDTO receberPedido(UUID codigo) throws Exception;
    PedidoDTO pagarPedido(UUID codigo) throws Exception ;
    PedidoDTO prepararPedido(UUID codigo) throws Exception ;
    PedidoDTO prontificarPedido(UUID codigo) throws Exception ;
    PedidoDTO finalizarPedido(UUID codigo) throws Exception ;
    PedidoDTO cancelarPedido(UUID codigo) throws Exception ;
}
