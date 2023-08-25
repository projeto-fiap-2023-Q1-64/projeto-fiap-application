package br.fiap.projeto.contexto.pedido.adapter.controller;

import br.fiap.projeto.contexto.pedido.adapter.controller.port.IPedidoManagementRestAdapterController;
import br.fiap.projeto.contexto.pedido.adapter.controller.rest.response.PedidoDTO;
import br.fiap.projeto.contexto.pedido.adapter.mapper.PedidoDtoMapper;
import br.fiap.projeto.contexto.pedido.usecase.port.IPedidoManagementUseCase;

import java.util.UUID;

public class PedidoManagementRestAdapterController implements IPedidoManagementRestAdapterController {
    private final IPedidoManagementUseCase pedidoManagementUseCase;

    public PedidoManagementRestAdapterController(IPedidoManagementUseCase pedidoManagementUseCase){
        this.pedidoManagementUseCase = pedidoManagementUseCase;
    }

    @Override
    public PedidoDTO criaPedido(String codigoCliente) {
        UUID cliente = null;
        if(codigoCliente != null && !codigoCliente.isEmpty()){
            cliente = UUID.fromString(codigoCliente);
        }

        return PedidoDtoMapper.toDto(this.pedidoManagementUseCase.criaPedido(cliente));
    }

    @Override
    public PedidoDTO adicionarProduto(UUID codigoPedido, UUID codigoProduto) throws Exception {
        return PedidoDtoMapper.toDto(this.pedidoManagementUseCase.adicionarProduto(codigoPedido, codigoProduto));
    }

    @Override
    public void removerProduto(UUID codigoPedido, UUID produtoCodigo) throws Exception {
        this.pedidoManagementUseCase.removerProduto(codigoPedido, produtoCodigo);
    }

    @Override
    public PedidoDTO aumentarQuantidade(UUID codigoPedido, UUID produtoCodigo) throws Exception {
        return PedidoDtoMapper.toDto(this.pedidoManagementUseCase.aumentarQuantidade(codigoPedido, produtoCodigo));
    }

    @Override
    public PedidoDTO reduzirQuantidade(UUID codigoPedido, UUID produtoCodigo) throws Exception {
        return PedidoDtoMapper.toDto(this.pedidoManagementUseCase.reduzirQuantidade(codigoPedido, produtoCodigo));
    }
}
