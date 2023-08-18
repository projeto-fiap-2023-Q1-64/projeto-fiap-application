package br.fiap.projeto.contexto.produto.usecase.exception;

public class EntradaInvalidaException extends BaseException {
    private final static String DEFAULT_MESSAGE = "Entrada inválida!";
    private final static int CODE = 3003;
    public EntradaInvalidaException() {
        super(CODE, DEFAULT_MESSAGE);
    }
    public EntradaInvalidaException(String message) {
        super(CODE, message);
    }
}
