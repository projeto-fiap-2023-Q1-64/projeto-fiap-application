package br.fiap.projeto.contexto.pedido.domain.exception;

public class ItemNotFoundException extends Exception{
    public ItemNotFoundException(String message) {
        super(message);
    }
}
