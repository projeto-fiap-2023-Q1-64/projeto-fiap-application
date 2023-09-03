package br.fiap.projeto.contexto.comanda.usecase.exception;

public class ComandaDuplicadaException extends Exception {
    public ComandaDuplicadaException(String mensagem) {
        super(mensagem);
    }
}
