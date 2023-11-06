package br.fiap.projeto.contexto.produto;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import br.fiap.projeto.contexto.produto.entity.Produto;
import br.fiap.projeto.contexto.produto.entity.enums.CategoriaProduto;
import br.fiap.projeto.contexto.produto.usecase.exception.EntradaInvalidaException;

public class ProdutoValidacoesTest {

    @Test
    public void criaProdutoComParametrosValidosComChave() throws EntradaInvalidaException {
        assertDoesNotThrow(
                () -> new Produto("123", "Hamburguer", "Lanche para comer bem bão", 20.52,
                        CategoriaProduto.LANCHE,
                        "imagem", 10));
    }

    @Test
    public void criaProdutoSemChave() throws EntradaInvalidaException {

        assertThrows(
                EntradaInvalidaException.class,
                () -> new Produto(null, "Hamburguer", "Lanche para comer bem bão", 20.52,
                        CategoriaProduto.LANCHE,
                        "imagem", 10),
                "Mensagem de erro");
    }

    @Test
    public void criaProdutoSemNome() throws EntradaInvalidaException {

        assertThrows(
                EntradaInvalidaException.class,
                () -> new Produto("123", null, "Lanche para comer bem bão", 20.52,
                        CategoriaProduto.LANCHE,
                        "imagem", 10),
                "Mensagem de erro");
    }

    @Test
    public void criaProdutoSemDescricao() throws EntradaInvalidaException {

        assertThrows(
                EntradaInvalidaException.class,
                () -> new Produto("123", "Hamburguer", null, 20.52,
                        CategoriaProduto.LANCHE,
                        "imagem", 10),
                "Mensagem de erro");
    }

    @Test
    public void criaProdutoSemPreco() throws EntradaInvalidaException {

        assertThrows(
                EntradaInvalidaException.class,
                () -> new Produto("123", "Hamburguer", "Lanche para comer bem bão", null,
                        CategoriaProduto.LANCHE,
                        "imagem", 10),
                "Mensagem de erro");
    }

    @Test
    public void criaProdutoSemCategoria() throws EntradaInvalidaException {

        assertThrows(
                NullPointerException.class,
                () -> new Produto("123", "Hamburguer", "Lanche para comer bem bão", 20.52,
                        null,
                        "imagem", 10),
                "Mensagem de erro");
    }

    @Test
    public void criaProdutoSemTempoPreparoMinimo() throws EntradaInvalidaException {

        assertThrows(
                EntradaInvalidaException.class,
                () -> new Produto("123", "Hamburguer", "Lanche para comer bem bão", 20.52,
                        CategoriaProduto.LANCHE,
                        "imagem", null),
                "Mensagem de erro");
    }

    @Test
    public void criaProdutoComTempoPreparoMinimoZero() throws EntradaInvalidaException {

        assertThrows(
                EntradaInvalidaException.class,
                () -> new Produto("123", "Hamburguer", "Lanche para comer bem bão", 20.52,
                        CategoriaProduto.LANCHE,
                        "imagem", 0),
                "Mensagem de erro");
    }

    @Test
    public void criaProdutoComTempoPreparoMinimoNegativo() throws EntradaInvalidaException {

        assertThrows(
                EntradaInvalidaException.class,
                () -> new Produto("123", "Hamburguer", "Lanche para comer bem bão", 20.52,
                        CategoriaProduto.LANCHE,
                        "imagem", -10),
                "Mensagem de erro");
    }

}
