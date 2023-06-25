package br.fiap.projeto.contexto.identificacao.application.entity;

import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

public class MensagemErroDTO {
    private List<String> erros;

    public MensagemErroDTO(List<String> erros) {

        this.erros = erros;
    }

    public MensagemErroDTO(String erro) {

        this.erros = Arrays.asList(erro);
    }

    public List<String> getErros() {
        return erros;
    }
}
