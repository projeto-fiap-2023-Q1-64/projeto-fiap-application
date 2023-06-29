package br.fiap.projeto.contexto.produto.application.exception;

public class ProdutoResponseException {
    private Integer codigo;
    private String mensagem;
    private String causa;

    public ProdutoResponseException(Integer codigo, String mensagem, Throwable causa) {
        this.codigo = codigo;
        this.mensagem = mensagem;
        this.causa = causa != null ? causa.getMessage() : null;
    }

    public ProdutoResponseException(Exception e) {
        this.codigo = 500;
        this.mensagem = e.getMessage();
        this.causa = e.getCause() != null ? e.getCause().getMessage() : null;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public String getCausa() {
        return causa;
    }
}
