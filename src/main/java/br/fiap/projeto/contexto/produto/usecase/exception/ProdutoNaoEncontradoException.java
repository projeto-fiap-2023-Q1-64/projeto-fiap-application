package br.fiap.projeto.contexto.produto.usecase.exception;

public class ProdutoNaoEncontradoException extends BaseException {
    private final static String DEFAULT_MESSAGE = "Produto não encontrado!";
    private final static int CODE = 3001;

    public ProdutoNaoEncontradoException() {
        super(CODE, DEFAULT_MESSAGE);
    }

    public ProdutoNaoEncontradoException(String message) {
        super(CODE, message);
    }
}
