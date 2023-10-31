package br.fiap.projeto.contexto.pedido;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import br.fiap.projeto.contexto.pedido.entity.Pedido;
import br.fiap.projeto.contexto.pedido.entity.enums.StatusPedido;
import br.fiap.projeto.contexto.pedido.usecase.exception.NoItensException;

public class PedidoValidacoesTest {

    @Test
    public void PedidoOk() {
        assertDoesNotThrow(
                () -> new Pedido(UUID.randomUUID(), null, UUID.randomUUID(), StatusPedido.INICIADO, 20d,
                        LocalDateTime.now()),
                "Codigo: 200");
    }

    @Test
    public void codigoPedidoNulo() {
        assertThrows(
                NoItensException.class,
                () -> new Pedido(null, null, UUID.randomUUID(), StatusPedido.INICIADO, 20d,
                        LocalDateTime.now()),
                "Mensagem de erro");
    }

    @Test
    public void codigoClienteNulo() {
        assertThrows(
                NoItensException.class,
                () -> new Pedido(UUID.randomUUID(), null, null, StatusPedido.INICIADO, 20d,
                        LocalDateTime.now()),
                "Mensagem de erro");
    }

    @Test
    public void valorTotalNulo() {
        assertThrows(
                NoItensException.class,
                () -> new Pedido(UUID.randomUUID(), null, UUID.randomUUID(), StatusPedido.INICIADO, null,
                        LocalDateTime.now()),
                "Mensagem de erro");
    }

    @Test
    public void valorTotalZero() {
        assertThrows(
                NoItensException.class,
                () -> new Pedido(UUID.randomUUID(), null, UUID.randomUUID(), StatusPedido.INICIADO, 0d,
                        LocalDateTime.now()),
                "Mensagem de erro");
    }

    @Test
    public void valorTotalNegativo() {
        assertThrows(
                NoItensException.class,
                () -> new Pedido(UUID.randomUUID(), null, UUID.randomUUID(), StatusPedido.INICIADO, -10d,
                        LocalDateTime.now()),
                "Mensagem de erro");
    }

    @Test
    public void dataCriacaoNulo() {
        assertThrows(
                NoItensException.class,
                () -> new Pedido(UUID.randomUUID(), null, UUID.randomUUID(), StatusPedido.INICIADO, -10d,
                        null),
                "Mensagem de erro");
    }

    @Test
    public void statusPedidoNulo() {
        assertThrows(
                NullPointerException.class,
                () -> new Pedido(UUID.randomUUID(), null, UUID.randomUUID(), null, 20d,
                        LocalDateTime.now()),
                "Codigo: 200");
    }

}
