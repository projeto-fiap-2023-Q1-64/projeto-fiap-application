package br.fiap.projeto.contexto.identificacao.domain.vo;


import br.fiap.projeto.exception.InvalidInputException;

import java.util.regex.Pattern;

public class Cpf {
 
	private String numero;
	 
	public void validar() throws InvalidInputException {

		if (Pattern.matches("\\d{11}", numero)) {
			return;
		}
		throw new InvalidInputException("Cpf inválido!");
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}
}
 
