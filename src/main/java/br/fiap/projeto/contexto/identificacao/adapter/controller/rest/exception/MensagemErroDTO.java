package br.fiap.projeto.contexto.identificacao.adapter.controller.rest.exception;

public class MensagemErroDTO {

    private int code;
    private String erro;

    public MensagemErroDTO(int code, String erro) {

        this.code = code;
        this.erro = erro;
    }

    public int getCode() {
        return code;
    }

    public String getErro() {
        return erro;
    }
}
