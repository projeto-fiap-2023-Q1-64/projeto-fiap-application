package br.fiap.projeto.contexto.identificacao.domain.vo;

import br.fiap.projeto.exception.InvalidInputException;

import java.util.regex.Pattern;

public class Email {
 
	private String endereco;
	 
	public void validar() throws InvalidInputException {

		if (Pattern.matches("\\s+@\\s+\\.\\s+[\\.\\s*]*", endereco)) {
			return;
		}
		throw new InvalidInputException("E-mail inválido!");
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
}
 
