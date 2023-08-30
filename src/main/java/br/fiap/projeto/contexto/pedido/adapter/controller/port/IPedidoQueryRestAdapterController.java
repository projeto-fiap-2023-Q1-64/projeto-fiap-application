package br.fiap.projeto.contexto.pedido.adapter.controller.port;

import br.fiap.projeto.contexto.pedido.adapter.controller.rest.response.PedidoDTO;

import java.util.List;

public interface IPedidoQueryRestAdapterController {
    List<PedidoDTO> buscarTodosRecebido();
    List<PedidoDTO> buscarTodosPagos();
    List<PedidoDTO> buscarTodosEmPreparacao();
    List<PedidoDTO> buscarTodosPronto();
    List<PedidoDTO> buscarTodosFinalizado();
    List<PedidoDTO> buscarPorStatusEData();
}
