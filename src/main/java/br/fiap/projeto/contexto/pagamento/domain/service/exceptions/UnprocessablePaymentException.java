package br.fiap.projeto.contexto.pagamento.domain.service.exceptions;

public class UnprocessablePaymentException extends RuntimeException{
    public UnprocessablePaymentException(String msg) {
        super(msg);
    }
}