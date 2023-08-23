package br.fiap.projeto.contexto.identificacao.entity.vo;

import br.fiap.projeto.contexto.identificacao.usecase.exception.EntradaInvalidaException;

import java.util.regex.Pattern;

public class Email {

    public final static String EMAIL_INVALIDO = "E-mail inválido!";
    private final String endereco;

    public Email(String endereco) {
        this.endereco = endereco;
    }

    public void validar() throws EntradaInvalidaException {
        if (Pattern.matches("\\w+@\\w+\\.\\w+[\\.\\w*]*", endereco)) {
            return;
        }
        throw new EntradaInvalidaException(EMAIL_INVALIDO);
    }

    public static Email fromString(String endereco) {
        return new Email(endereco);
    }

    public String getEndereco() {
        return endereco;
    }
}

