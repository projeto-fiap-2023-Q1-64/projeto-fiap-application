package br.fiap.projeto.contexto.pedido.adapter.controller;

import br.fiap.projeto.contexto.pedido.adapter.controller.port.IPedidoQueryRestAdapterController;
import br.fiap.projeto.contexto.pedido.adapter.controller.rest.response.PedidoDTO;
import br.fiap.projeto.contexto.pedido.adapter.mapper.PedidoDtoMapper;
import br.fiap.projeto.contexto.pedido.entity.Pedido;
import br.fiap.projeto.contexto.pedido.usecase.port.usecase.IPedidoQueryUseCase;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class PedidoQueryRestAdapterController implements IPedidoQueryRestAdapterController {
    private final IPedidoQueryUseCase queryUseCase;

    public PedidoQueryRestAdapterController(IPedidoQueryUseCase queryUseCase) {
        this.queryUseCase = queryUseCase;
    }

    @Override
    public PedidoDTO buscaPedido(UUID codigoPedido) {
        return PedidoDtoMapper.toDto(this.queryUseCase.buscaPedido(codigoPedido));
    }

    @Override
    public List<PedidoDTO> buscarTodosRecebido() {
        return this.convertList(this.queryUseCase.buscarTodosRecebido());
    }

    @Override
    public List<PedidoDTO> buscarTodosPagos() {
        return this.convertList(this.queryUseCase.buscarTodosPagos());
    }

    @Override
    public List<PedidoDTO> buscarTodosEmPreparacao() {
        return this.convertList(this.queryUseCase.buscarTodosEmPreparacao());
    }

    @Override
    public List<PedidoDTO> buscarTodosPronto() {
        return this.convertList(this.queryUseCase.buscarTodosPronto());
    }

    @Override
    public List<PedidoDTO> buscarTodosFinalizado() {
        return this.convertList(this.queryUseCase.buscarTodosFinalizado());
    }

    @Override
    public List<PedidoDTO> buscarTodosCancelado() {
        return this.convertList(this.queryUseCase.buscarTodosCancelado());
    }

    @Override
    public List<PedidoDTO> buscarPorStatusEData() {
        return this.convertList(this.queryUseCase.buscarTodosPorStatusEDataCriacao());
    }

    private List<PedidoDTO> convertList(List<Pedido> pedidos){
        return pedidos.stream().map(PedidoDtoMapper::toDto).collect(Collectors.toList());
    }
}
