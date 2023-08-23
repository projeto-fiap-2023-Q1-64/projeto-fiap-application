package br.fiap.projeto.contexto.pedido.adapter.controller;

import br.fiap.projeto.contexto.pedido.adapter.controller.port.IPedidoWorkFlowRestAdapterController;
import br.fiap.projeto.contexto.pedido.adapter.controller.rest.response.PedidoDTO;
import br.fiap.projeto.contexto.pedido.external.integration.PedidoClienteIntegration;
import br.fiap.projeto.contexto.pedido.external.integration.PedidoProdutoIntegration;
import br.fiap.projeto.contexto.pedido.usecase.port.IPedidoManagementUseCase;
import br.fiap.projeto.contexto.pedido.usecase.port.IPedidoWorkFlowUseCase;

import java.util.UUID;

public class PedidoWorkFlowRestAdapterController implements IPedidoWorkFlowRestAdapterController {
    private final IPedidoWorkFlowUseCase workFlowUseCase;
    public PedidoWorkFlowRestAdapterController(IPedidoWorkFlowUseCase workFlowUseCase) {
        this.workFlowUseCase = workFlowUseCase;
    }
    @Override
    public PedidoDTO receberPedido(UUID codigo) throws Exception {
        return this.workFlowUseCase.receber(codigo);
    }

    @Override
    public PedidoDTO pagarPedido(UUID codigo) throws Exception {
        return this.workFlowUseCase.pagar(codigo);
    }

    @Override
    public PedidoDTO prepararPedido(UUID codigo) throws Exception {
        return this.workFlowUseCase.preparar(codigo);
    }

    @Override
    public PedidoDTO prontificarPedido(UUID codigo) throws Exception {
        return this.workFlowUseCase.prontificar(codigo);
    }

    @Override
    public PedidoDTO finalizarPedido(UUID codigo) throws Exception {
        return this.workFlowUseCase.finalizar(codigo);
    }
}
