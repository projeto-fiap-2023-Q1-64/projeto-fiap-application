package br.fiap.projeto.contexto.pedido.usecase.port.adaptergateway;

import br.fiap.projeto.contexto.pedido.entity.integration.PagamentoPedido;

import java.util.UUID;

public interface IPedidoPagamentoIntegrationAdapterGateway {
    PagamentoPedido buscaStatusPagamentoPorCodigoPedido(UUID codigoPedido);
}
