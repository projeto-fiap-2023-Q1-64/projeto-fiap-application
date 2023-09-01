package br.fiap.projeto.contexto.pedido.adapter.controller.port;

import br.fiap.projeto.contexto.pedido.adapter.controller.rest.response.PedidoDTO;

import java.util.List;
import java.util.UUID;

public interface IPedidoQueryRestAdapterController {
    PedidoDTO buscaPedido(UUID codigoPedido);
    List<PedidoDTO> buscarTodosRecebido();
    List<PedidoDTO> buscarTodosPagos();
    List<PedidoDTO> buscarTodosEmPreparacao();
    List<PedidoDTO> buscarTodosPronto();
    List<PedidoDTO> buscarTodosFinalizado();
    List<PedidoDTO> buscarTodosCancelado();
    List<PedidoDTO> buscarPorStatusEData();
}
