package br.fiap.projeto.contexto.produto.usecase.exception;

public abstract class BaseException extends Exception {

    public final static int DEFAULT_CODE = 3000;
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

    public int getCode() {
        return code;
    }
}
