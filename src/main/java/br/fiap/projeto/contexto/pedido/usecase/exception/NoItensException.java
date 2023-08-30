package br.fiap.projeto.contexto.pedido.usecase.exception;

public class NoItensException extends Exception{
    public NoItensException(String message) {
        super( "O Pedido " + message + "não possui itens cadastrados !");
    }
}
