package br.fiap.projeto.contexto.pedido.domain.exception;

public class InvalidStatusException extends Exception{
    public InvalidStatusException(String message) {
        super(message);
    }
}