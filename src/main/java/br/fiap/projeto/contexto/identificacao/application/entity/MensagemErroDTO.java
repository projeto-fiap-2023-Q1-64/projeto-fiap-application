package br.fiap.projeto.contexto.identificacao.application.entity;

import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

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
