package br.fiap.projeto.contexto.pedido.usecase.port.adaptergateway;

import java.util.UUID;

public interface IPedidoClienteIntegrationAdapterGateway {
    Boolean verificaClienteExite(UUID codigoCliente);
}
