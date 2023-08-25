package br.fiap.projeto.contexto.pedido.usecase.port;

import java.util.UUID;

public interface IPedidoClienteIntegrationAdapterGateway {
    Boolean VerificaClienteExite(UUID codigoCliente);
}
