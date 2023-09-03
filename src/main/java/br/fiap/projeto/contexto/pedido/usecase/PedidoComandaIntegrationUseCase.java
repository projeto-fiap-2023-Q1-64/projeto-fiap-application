package br.fiap.projeto.contexto.pedido.usecase;

import br.fiap.projeto.contexto.pedido.entity.Pedido;
import br.fiap.projeto.contexto.pedido.entity.enums.StatusPedido;
import br.fiap.projeto.contexto.pedido.entity.integration.ComandaPedido;
import br.fiap.projeto.contexto.pedido.usecase.enums.MensagemErro;
import br.fiap.projeto.contexto.pedido.usecase.port.adaptergateway.IPedidoComandaIntegrationAdapterGateway;
import br.fiap.projeto.contexto.pedido.usecase.port.usecase.IPedidoComandaIntegrationUseCase;
import br.fiap.projeto.contexto.pedido.usecase.port.usecase.IPedidoWorkFlowUseCase;

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
        Pedido pedido;
        pedido = this.pedidoWorkFlowUseCase.preparar(codigoPedido);
        if (pedido == null || !pedido.getStatus().equals(StatusPedido.EM_PREPARACAO)) {
            throw new Exception(MensagemErro.STATUS_UPDATE_ERROR.getMessage());
        }
        ComandaPedido comandaPedido;
        try {
            comandaPedido = pedidoComandaIntegrationAdapterGateway.criaComanda(codigoPedido);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(MensagemErro.COMANDA_INTEGRATION_ERROR.getMessage());
        }
        if (comandaPedido == null || comandaPedido.getCodigoComanda().toString().isEmpty()) {
            throw new Exception(MensagemErro.COMANDA_CREATE_ERROR.getMessage());
        }
        return pedido;
    }
}
