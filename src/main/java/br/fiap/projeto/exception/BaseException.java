package br.fiap.projeto.exception;

public class BaseException extends Exception {

    private String message;

    public BaseException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
