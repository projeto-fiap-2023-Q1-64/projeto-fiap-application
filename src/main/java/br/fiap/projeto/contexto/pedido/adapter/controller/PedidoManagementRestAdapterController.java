package br.fiap.projeto.contexto.pedido.adapter.controller;

import br.fiap.projeto.contexto.pedido.adapter.controller.port.IPedidoManagementRestAdapterController;
import br.fiap.projeto.contexto.pedido.adapter.controller.rest.request.PedidoCriarDTO;
import br.fiap.projeto.contexto.pedido.adapter.controller.rest.request.ProdutoPedidoDTO;
import br.fiap.projeto.contexto.pedido.adapter.controller.rest.response.PedidoDTO;
import br.fiap.projeto.contexto.pedido.usecase.port.IPedidoManagementUseCase;

import java.util.UUID;

public class PedidoManagementRestAdapterController implements IPedidoManagementRestAdapterController {
    private final IPedidoManagementUseCase pedidoManagementUseCase;

    public PedidoManagementRestAdapterController(IPedidoManagementUseCase pedidoManagementUseCase){
        this.pedidoManagementUseCase = pedidoManagementUseCase;
    }

    @Override
    public PedidoDTO criaPedido(PedidoCriarDTO pedidoCriarDTO) {
        return this.pedidoManagementUseCase.criaPedido(pedidoCriarDTO);
    }

    @Override
    public PedidoDTO adicionarProduto(UUID codigoPedido, ProdutoPedidoDTO produtoPedidoDTO) throws Exception {
        return this.pedidoManagementUseCase.adicionarProduto(codigoPedido, produtoPedidoDTO);
    }

    @Override
    public void removerProduto(UUID codigoPedido, UUID produtoCodigo) throws Exception {
        this.pedidoManagementUseCase.removerProduto(codigoPedido, produtoCodigo);
    }

    @Override
    public PedidoDTO aumentarQuantidade(UUID codigoPedido, UUID produtoCodigo) throws Exception {
        return this.pedidoManagementUseCase.aumentarQuantidade(codigoPedido, produtoCodigo);
    }

    @Override
    public PedidoDTO reduzirQuantidade(UUID codigoPedido, UUID produtoCodigo) throws Exception {
        return this.pedidoManagementUseCase.reduzirQuantidade(codigoPedido, produtoCodigo);
    }
}
