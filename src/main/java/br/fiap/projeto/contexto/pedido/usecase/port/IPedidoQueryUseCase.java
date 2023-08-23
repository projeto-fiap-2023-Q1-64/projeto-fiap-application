package br.fiap.projeto.contexto.pedido.usecase.port;

import br.fiap.projeto.contexto.pedido.adapter.controller.rest.response.PedidoDTO;

import java.util.List;
import java.util.UUID;

public interface IPedidoQueryUseCase {
    PedidoDTO buscaPedido(UUID codigo);
    List<PedidoDTO> buscaTodos();
    List<PedidoDTO> buscarTodosRecebido();
    List<PedidoDTO> buscarTodosPagos();
    List<PedidoDTO> buscarTodosEmPreparacao();
    List<PedidoDTO> buscarTodosPronto();
    List<PedidoDTO> buscarTodosFinalizado();
}
