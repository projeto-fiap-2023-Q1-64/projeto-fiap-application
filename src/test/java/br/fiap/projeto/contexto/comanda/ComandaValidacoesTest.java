package br.fiap.projeto.contexto.comanda;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.UUID;

import org.junit.jupiter.api.Test;

import br.fiap.projeto.contexto.comanda.entity.Comanda;
import br.fiap.projeto.contexto.comanda.entity.enums.StatusComanda;
import br.fiap.projeto.contexto.comanda.usecase.exception.EntradaInvalidaException;

public class ComandaValidacoesTest {

    @Test
    public void codigoComandaNulo() throws EntradaInvalidaException {

        assertThrows(
                EntradaInvalidaException.class,
                () -> new Comanda(null, UUID.randomUUID(), StatusComanda.RECEBIDO),
                "Mensagem de erro");
    }

    @Test
    public void codigoPedidoNulo() {

        assertThrows(
                EntradaInvalidaException.class,
                () -> new Comanda(UUID.randomUUID(), null, StatusComanda.RECEBIDO),
                "Mensagem de erro");
    }

    @Test
    public void statusComandaNulo() {

        assertThrows(
                NullPointerException.class,
                () -> new Comanda(UUID.randomUUID(), UUID.randomUUID(), null),
                "Mensagem de erro");
    }

    @Test
    public void comandaOk() {

        assertDoesNotThrow(
                () -> new Comanda(UUID.randomUUID(), UUID.randomUUID(), StatusComanda.RECEBIDO),
                "Mensagem de erro");
    }
}
