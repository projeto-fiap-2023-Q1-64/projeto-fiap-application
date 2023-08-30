package br.fiap.projeto.contexto.pedido.entity.integration;

import java.util.UUID;

public class ComandaPedido {
    private UUID codigoComanda;
    private UUID codigoPedido;

    public ComandaPedido() {
    }

    public ComandaPedido(UUID codigoComanda, UUID codigoPedido) {
        this.codigoComanda = codigoComanda;
        this.codigoPedido = codigoPedido;
    }

    public UUID getCodigoComanda() {
        return codigoComanda;
    }

    public UUID getCodigoPedido() {
        return codigoPedido;
    }
}