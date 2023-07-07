package br.fiap.projeto.contexto.identificacao.domain.exception;

public class EntidadeNaoEncontradaException extends BaseException {

    public EntidadeNaoEncontradaException(String message) {
        super(4001, message);
    }

}
