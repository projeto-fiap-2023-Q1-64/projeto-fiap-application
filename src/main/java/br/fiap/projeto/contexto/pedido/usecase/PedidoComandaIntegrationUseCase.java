package br.fiap.projeto.contexto.pedido.usecase;

import br.fiap.projeto.contexto.pedido.adapter.controller.rest.response.PedidoDTO;
import br.fiap.projeto.contexto.pedido.entity.Pedido;
import br.fiap.projeto.contexto.pedido.entity.enums.StatusPedido;
import br.fiap.projeto.contexto.pedido.entity.integration.ComandaPedido;
import br.fiap.projeto.contexto.pedido.external.integration.port.Comanda;
import br.fiap.projeto.contexto.pedido.external.integration.port.CriaComanda;
import br.fiap.projeto.contexto.pedido.usecase.port.IPedidoComandaIntegrationAdapterGateway;
import br.fiap.projeto.contexto.pedido.usecase.port.IPedidoComandaIntegrationUseCase;
import br.fiap.projeto.contexto.pedido.usecase.port.IPedidoWorkFlowUseCase;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public class PedidoComandaIntegrationUseCase implements IPedidoComandaIntegrationUseCase {
    private final IPedidoComandaIntegrationAdapterGateway pedidoComandaIntegrationAdapterGateway;
    private final IPedidoWorkFlowUseCase pedidoWorkFlowUseCase;
    public PedidoComandaIntegrationUseCase(IPedidoComandaIntegrationAdapterGateway pedidoComandaIntegrationAdapterGateway,
                                           IPedidoWorkFlowUseCase pedidoWorkFlowUseCase) {
        this.pedidoComandaIntegrationAdapterGateway = pedidoComandaIntegrationAdapterGateway;
        this.pedidoWorkFlowUseCase = pedidoWorkFlowUseCase;
    }

    @Override
    public Pedido criaComanda(UUID codigoPedido) throws Exception {
        ComandaPedido comandaPedido = pedidoComandaIntegrationAdapterGateway.criaComanda(codigoPedido);
        if (comandaPedido == null || comandaPedido.getCodigoComanda().toString().isEmpty()) {
            System.out.println("Erro na criação da comanda!");
            throw new Exception("Erro na criação da comanda!");
        }

        Pedido pedido;
        try {
            pedido = this.pedidoWorkFlowUseCase.preparar(codigoPedido);
            if (pedido == null || !pedido.getStatus().equals(StatusPedido.EM_PREPARACAO)) {
                System.out.println("Erro na atualização do status!");
                throw new Exception("Erro na atualização do status!");
            }
        } catch (Exception e) {
            System.out.println("Erro na integração com a Comanda!");
            throw new Exception("Erro na integração com a Comanda!");
        }
        return pedido;
    }
}