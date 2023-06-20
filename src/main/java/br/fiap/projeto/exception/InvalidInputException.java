package br.fiap.projeto.exception;

public class InvalidInputException extends Exception {

    private String message;

    public InvalidInputException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
