package br.fiap.projeto.contexto.identificacao.usecase.exception;

public abstract class BaseException extends Exception {
    public final static int DEFAULT_CODE = 4000;
    private final int code;
    private final String message;

    public BaseException(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }
}
