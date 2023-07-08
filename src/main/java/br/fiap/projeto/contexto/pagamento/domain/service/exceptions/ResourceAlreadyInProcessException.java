package br.fiap.projeto.contexto.pagamento.domain.service.exceptions;
public class ResourceAlreadyInProcessException extends RuntimeException{
    public ResourceAlreadyInProcessException(String msg) {
        super(msg);
    }
}
