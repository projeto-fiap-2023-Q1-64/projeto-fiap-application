package br.fiap.projeto.contexto.identificacao.domain.exception;

public class EntradaInvalidaException extends BaseException {

    public EntradaInvalidaException(String message) {
        super(4002, message);
    }
}
