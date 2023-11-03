package br.fiap.projeto.contexto.pedido;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import br.fiap.projeto.contexto.pedido.entity.ItemPedido;
import br.fiap.projeto.contexto.pedido.entity.Pedido;
import br.fiap.projeto.contexto.pedido.entity.enums.CategoriaProduto;
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
                                () -> new Pedido(UUID.randomUUID(), null, UUID.randomUUID(), StatusPedido.INICIADO,
                                                null,
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
                                () -> new Pedido(UUID.randomUUID(), null, UUID.randomUUID(), StatusPedido.INICIADO,
                                                -10d,
                                                LocalDateTime.now()),
                                "Mensagem de erro");
        }

        @Test
        public void dataCriacaoNulo() {
                assertThrows(
                                NoItensException.class,
                                () -> new Pedido(UUID.randomUUID(), null, UUID.randomUUID(), StatusPedido.INICIADO,
                                                -10d,
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

        @Test
        public void itemPedidoOk() {
                assertDoesNotThrow(
                                () -> new ItemPedido(UUID.randomUUID(), UUID.randomUUID(), null, 10, "produtoNome",
                                                "produtoDescricao", 10d, CategoriaProduto.ACOMPANHAMENTO, "imagem",
                                                10));
        }

        @Test
        public void codigoItemPedidoNulo() {
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
                                                "produtoDescricao", null, CategoriaProduto.ACOMPANHAMENTO, "imagem",
                                                10),
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
                                                "produtoDescricao", -10d, CategoriaProduto.ACOMPANHAMENTO, "imagem",
                                                10),
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
                                                "produtoDescricao", 10d, CategoriaProduto.ACOMPANHAMENTO, "imagem",
                                                null),
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
                                                "produtoDescricao", 10d, CategoriaProduto.ACOMPANHAMENTO, "imagem",
                                                -10),
                                "Mensagem de erro");
        }

}
