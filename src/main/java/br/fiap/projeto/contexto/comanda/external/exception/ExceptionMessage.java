package br.fiap.projeto.contexto.comanda.external.exception;

public class ExceptionMessage extends Exception {
    public ExceptionMessage(String mensagem) {
        super("Status inválido: " + mensagem);
    }
}
