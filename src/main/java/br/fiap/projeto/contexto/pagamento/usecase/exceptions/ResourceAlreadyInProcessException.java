package br.fiap.projeto.contexto.pagamento.usecase.exceptions;
public class ResourceAlreadyInProcessException extends RuntimeException{
    public ResourceAlreadyInProcessException(String msg) {
        super(msg);
    }
}
