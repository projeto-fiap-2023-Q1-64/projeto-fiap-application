package br.fiap.projeto.contexto.pedido.usecase.exception;

public class ItemNotFoundException extends Exception{
    public ItemNotFoundException(String message) {
        super(message);
    }
}
