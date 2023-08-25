package br.fiap.projeto.contexto.pedido.adapter.gateway;

import br.fiap.projeto.contexto.pedido.adapter.mapper.ComandaMapper;
import br.fiap.projeto.contexto.pedido.entity.integration.ComandaPedido;
import br.fiap.projeto.contexto.pedido.external.integration.PedidoComandaIntegration;
import br.fiap.projeto.contexto.pedido.usecase.port.IPedidoComandaIntegrationAdapterGateway;

import java.util.UUID;

public class PedidoComandaIntegrationAdapterGateway implements IPedidoComandaIntegrationAdapterGateway {
    private final PedidoComandaIntegration pedidoComandaIntegration;

    public PedidoComandaIntegrationAdapterGateway(PedidoComandaIntegration pedidoComandaIntegration) {
        this.pedidoComandaIntegration = pedidoComandaIntegration;
    }

    @Override
    public ComandaPedido criaComanda(UUID codigoProduto) {
        return ComandaMapper.toComandaPedido(
                pedidoComandaIntegration.criaComanda(
                        ComandaMapper.toCriaComanda(codigoProduto)));
    }
}
