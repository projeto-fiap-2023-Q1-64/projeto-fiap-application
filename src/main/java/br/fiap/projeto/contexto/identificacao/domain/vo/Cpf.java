package br.fiap.projeto.contexto.identificacao.domain.vo;


import br.fiap.projeto.contexto.identificacao.domain.exception.EntradaInvalidaException;

import java.util.regex.Pattern;

public class Cpf {

	public final static String CPF_INVALIDO = "Cpf inválido!";
	private String numero;

	public Cpf(String numero) {
		this.numero = numero;
	}

	public void validar() throws EntradaInvalidaException {

		if (Pattern.matches("\\d{11}", numero)) {
			return;
		}
		throw new EntradaInvalidaException(CPF_INVALIDO);
	}

	public String getNumero() {
		return numero;
	}

	public static Cpf fromString(String numero) {

		return new Cpf(numero);
	}
}
 
