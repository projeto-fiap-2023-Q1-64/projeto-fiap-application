package br.fiap.projeto.contexto.pedido.usecase.port.usecase;

import br.fiap.projeto.contexto.pedido.entity.Pedido;

import java.util.UUID;

public interface IPedidoPagamentoIntegrationUseCase {
    Pedido pagar(UUID codigoPedido) throws Exception;
}
