package br.fiap.projeto.contexto.pedido.usecase.exception;

public class InvalidStatusException extends Exception{
    public InvalidStatusException(String message) {
        super(message);
    }
}