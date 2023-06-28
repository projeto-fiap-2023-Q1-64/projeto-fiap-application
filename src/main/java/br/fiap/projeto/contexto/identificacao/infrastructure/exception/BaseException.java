package br.fiap.projeto.contexto.identificacao.infrastructure.exception;

public abstract class BaseException extends Exception {

    private final static int DEFAULT_CODE = 4000;

    private int code;
    private String message;

    public BaseException(int code, String message) {

        this.code = code;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public static int getCode() {
        return DEFAULT_CODE;
    }
}
