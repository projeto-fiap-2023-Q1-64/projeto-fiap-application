package br.fiap.projeto.contexto.pedido.usecase.port;

import br.fiap.projeto.contexto.pedido.entity.integration.ComandaPedido;

import java.util.UUID;

public interface IPedidoComandaIntegrationAdapterGateway {
    ComandaPedido criaComanda(UUID codigoProduto);
}
