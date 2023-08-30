package br.fiap.projeto.contexto.pagamento.usecase.exceptions;

public class UnprocessablePaymentException extends RuntimeException{
    public UnprocessablePaymentException(String msg) {
        super(msg);
    }
}