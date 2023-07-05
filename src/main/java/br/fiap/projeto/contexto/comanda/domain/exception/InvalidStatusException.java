package br.fiap.projeto.contexto.comanda.domain.exception;

public class InvalidStatusException extends Exception {

    public InvalidStatusException(String mensagem) {
        super("Status inválido: " + mensagem);
    }
}
