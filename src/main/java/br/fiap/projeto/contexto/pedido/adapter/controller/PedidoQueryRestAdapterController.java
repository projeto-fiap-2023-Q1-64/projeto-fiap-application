package br.fiap.projeto.contexto.pedido.adapter.controller;

import br.fiap.projeto.contexto.pedido.adapter.controller.port.IPedidoQueryRestAdapterController;
import br.fiap.projeto.contexto.pedido.adapter.controller.rest.response.PedidoDTO;
import br.fiap.projeto.contexto.pedido.usecase.port.IPedidoQueryUseCase;

import java.util.List;

public class PedidoQueryRestAdapterController implements IPedidoQueryRestAdapterController {
    private final IPedidoQueryUseCase queryUseCase;

    public PedidoQueryRestAdapterController(IPedidoQueryUseCase queryUseCase) {
        this.queryUseCase = queryUseCase;
    }

    @Override
    public List<PedidoDTO> buscarTodosRecebido() {
        return this.queryUseCase.buscarTodosRecebido();
    }

    @Override
    public List<PedidoDTO> buscarTodosPagos() {
        return this.queryUseCase.buscarTodosPagos();
    }

    @Override
    public List<PedidoDTO> buscarTodosEmPreparacao() {
        return this.queryUseCase.buscarTodosEmPreparacao();
    }

    @Override
    public List<PedidoDTO> buscarTodosPronto() {
        return this.queryUseCase.buscarTodosPronto();
    }

    @Override
    public List<PedidoDTO> buscarTodosFinalizado() {
        return this.queryUseCase.buscarTodosFinalizado();
    }
}
