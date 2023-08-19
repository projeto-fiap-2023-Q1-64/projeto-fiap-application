package br.fiap.projeto.contexto.pedido.usecase.exception;

public class InvalidOperacaoProdutoException extends Exception{
    public InvalidOperacaoProdutoException(String message) {
        super(message);
    }
}
