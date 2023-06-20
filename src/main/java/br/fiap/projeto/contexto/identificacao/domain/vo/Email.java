package br.fiap.projeto.contexto.identificacao.domain.vo;

import br.fiap.projeto.exception.InvalidInputException;

import java.util.regex.Pattern;

public class Email {

	public final static String EMAIL_INVALIDO = "E-mail inválido!";
	private String endereco;

	public Email(String endereco) {
		this.endereco = endereco;
	}

	public void validar() throws InvalidInputException {

		if (Pattern.matches("\\w+@\\w+\\.\\w+[\\.\\w*]*", endereco)) {
			return;
		}
		throw new InvalidInputException(EMAIL_INVALIDO);
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public static Email fromString(String endereco) {

		return new Email(endereco);
	}
}
 
