package br.fiap.projeto.contexto.comanda.usecase.exception;

public class ComandaNaoEncontradaException extends Exception {
    public ComandaNaoEncontradaException(String mensagem) {
        super(mensagem);
    }
}
