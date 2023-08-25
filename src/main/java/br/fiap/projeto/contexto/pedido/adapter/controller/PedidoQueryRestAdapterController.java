package br.fiap.projeto.contexto.pedido.adapter.controller;

import br.fiap.projeto.contexto.pedido.adapter.controller.port.IPedidoQueryRestAdapterController;
import br.fiap.projeto.contexto.pedido.adapter.controller.rest.response.PedidoDTO;
import br.fiap.projeto.contexto.pedido.adapter.mapper.PedidoDtoMapper;
import br.fiap.projeto.contexto.pedido.entity.Pedido;
import br.fiap.projeto.contexto.pedido.usecase.port.IPedidoQueryUseCase;

import java.util.List;
import java.util.stream.Collectors;

public class PedidoQueryRestAdapterController implements IPedidoQueryRestAdapterController {
    private final IPedidoQueryUseCase queryUseCase;

    public PedidoQueryRestAdapterController(IPedidoQueryUseCase queryUseCase) {
        this.queryUseCase = queryUseCase;
    }

    @Override
    public List<PedidoDTO> buscarTodosRecebido() {
        List<Pedido> pedidos = this.queryUseCase.buscarTodosRecebido();
        return pedidos.stream().map(PedidoDtoMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<PedidoDTO> buscarTodosPagos() {
        List<Pedido> pedidos = this.queryUseCase.buscarTodosPagos();
        return pedidos.stream().map(PedidoDtoMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<PedidoDTO> buscarTodosEmPreparacao() {
        List<Pedido> pedidos = this.queryUseCase.buscarTodosEmPreparacao();
        return pedidos.stream().map(PedidoDtoMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<PedidoDTO> buscarTodosPronto() {
        List<Pedido> pedidos = this.queryUseCase.buscarTodosPronto();
        return pedidos.stream().map(PedidoDtoMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<PedidoDTO> buscarTodosFinalizado() {
        List<Pedido> pedidos = this.queryUseCase.buscarTodosFinalizado();
        return pedidos.stream().map(PedidoDtoMapper::toDto).collect(Collectors.toList());
    }
}
