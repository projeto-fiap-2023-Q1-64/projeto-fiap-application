package br.fiap.projeto.contexto.pagamento.external.integration.exceptions;

public class InvalidOperationIntegrationException extends RuntimeException{
    public InvalidOperationIntegrationException(String msg) {
        super(msg);
    }
}
