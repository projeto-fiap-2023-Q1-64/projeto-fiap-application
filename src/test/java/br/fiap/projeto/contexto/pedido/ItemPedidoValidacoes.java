package br.fiap.projeto.contexto.pedido;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.UUID;

import org.junit.jupiter.api.Test;

import br.fiap.projeto.contexto.pedido.entity.ItemPedido;
import br.fiap.projeto.contexto.pedido.entity.enums.CategoriaProduto;
import br.fiap.projeto.contexto.pedido.usecase.exception.NoItensException;

public class ItemPedidoValidacoes {

    @Test
    public void itemPedidoOk() {
        assertDoesNotThrow(
                () -> new ItemPedido(UUID.randomUUID(), UUID.randomUUID(), null, 10, "produtoNome",
                        "produtoDescricao", 10d, CategoriaProduto.ACOMPANHAMENTO, "imagem", 10));
    }

    @Test
    public void codigoPedidoNulo() {
        assertThrows(
                NoItensException.class,
                () -> new ItemPedido(null, UUID.randomUUID(), null, 10, "produtoNome",
                        "produtoDescricao", 10d, CategoriaProduto.ACOMPANHAMENTO, "imagem", 10),
                "Mensagem de erro");

    }

    @Test
    public void codigoProdutoNulo() {
        assertThrows(
                NoItensException.class,
                () -> new ItemPedido(UUID.randomUUID(), null, null, 10, "produtoNome",
                        "produtoDescricao", 10d, CategoriaProduto.ACOMPANHAMENTO, "imagem", 10),
                "Mensagem de erro");

    }

    @Test
    public void qtdeTotalNulo() {
        assertThrows(
                NoItensException.class,
                () -> new ItemPedido(UUID.randomUUID(), UUID.randomUUID(), null, null, "produtoNome",
                        "produtoDescricao", 10d, CategoriaProduto.ACOMPANHAMENTO, "imagem", 10),
                "Mensagem de erro");
    }

    @Test
    public void qtdeTotalZero() {
        assertThrows(
                NoItensException.class,
                () -> new ItemPedido(UUID.randomUUID(), UUID.randomUUID(), null, 0, "produtoNome",
                        "produtoDescricao", 10d, CategoriaProduto.ACOMPANHAMENTO, "imagem", 10),
                "Mensagem de erro");
    }

    @Test
    public void produtoNomeNull() {
        assertThrows(
                NoItensException.class,
                () -> new ItemPedido(UUID.randomUUID(), UUID.randomUUID(), null, 10, null,
                        "produtoDescricao", 10d, CategoriaProduto.ACOMPANHAMENTO, "imagem", 10),
                "Mensagem de erro");
    }

    @Test
    public void produtoDescricaoNull() {
        assertThrows(
                NoItensException.class,
                () -> new ItemPedido(UUID.randomUUID(), UUID.randomUUID(), null, 10, "produtoNome",
                        null, 10d, CategoriaProduto.ACOMPANHAMENTO, "imagem", 10),
                "Mensagem de erro");
    }

    @Test
    public void valorUnitarioNulo() {
        assertThrows(
                NoItensException.class,
                () -> new ItemPedido(UUID.randomUUID(), UUID.randomUUID(), null, 1, "produtoNome",
                        "produtoDescricao", null, CategoriaProduto.ACOMPANHAMENTO, "imagem", 10),
                "Mensagem de erro");
    }

    @Test
    public void valorUnitarioZero() {
        assertThrows(
                NoItensException.class,
                () -> new ItemPedido(UUID.randomUUID(), UUID.randomUUID(), null, 10, "produtoNome",
                        "produtoDescricao", 0d, CategoriaProduto.ACOMPANHAMENTO, "imagem", 10),
                "Mensagem de erro");
    }

    @Test
    public void valorUnitarioNegativo() {
        assertThrows(
                NoItensException.class,
                () -> new ItemPedido(UUID.randomUUID(), UUID.randomUUID(), null, 10, "produtoNome",
                        "produtoDescricao", -10d, CategoriaProduto.ACOMPANHAMENTO, "imagem", 10),
                "Mensagem de erro");
    }

    @Test
    public void categoriaProdutoNull() {
        assertThrows(
                NullPointerException.class,
                () -> new ItemPedido(UUID.randomUUID(), UUID.randomUUID(), null, 10, "produtoNome",
                        "produtoDescricao", 10d, null, "imagem", 10),
                "Mensagem de erro");
    }

    @Test
    public void tempoPreparoNulo() {
        assertThrows(
                NoItensException.class,
                () -> new ItemPedido(UUID.randomUUID(), UUID.randomUUID(), null, 1, "produtoNome",
                        "produtoDescricao", 10d, CategoriaProduto.ACOMPANHAMENTO, "imagem", null),
                "Mensagem de erro");
    }

    @Test
    public void tempoPreparoZero() {
        assertThrows(
                NoItensException.class,
                () -> new ItemPedido(UUID.randomUUID(), UUID.randomUUID(), null, 10, "produtoNome",
                        "produtoDescricao", 10d, CategoriaProduto.ACOMPANHAMENTO, "imagem", 0),
                "Mensagem de erro");
    }

    @Test
    public void tempoPreparoNegativo() {
        assertThrows(
                NoItensException.class,
                () -> new ItemPedido(UUID.randomUUID(), UUID.randomUUID(), null, 10, "produtoNome",
                        "produtoDescricao", 10d, CategoriaProduto.ACOMPANHAMENTO, "imagem", -10),
                "Mensagem de erro");
    }

}