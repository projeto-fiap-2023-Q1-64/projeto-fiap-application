package br.fiap.projeto.contexto.identificacao.domain.entity;

import br.fiap.projeto.contexto.identificacao.domain.vo.Cpf;
import br.fiap.projeto.contexto.identificacao.domain.vo.Email;
import br.fiap.projeto.exception.InvalidInputException;

import java.util.UUID;

public class Cliente {

	public final static String CPF_AUSENTE = "Informe o cpf!";
	public final static String EMAIL_AUSENTE = "Informe o e-mail!";
	public final static String NOME_AUSENTE = "Informe o nome!";
 
	private final UUID codigo;
	 
	private final String nome;
	 
	private final Cpf cpf;
	 
	private final Email email;

	public Cliente(UUID codigo, String nome, Cpf cpf, Email email) throws InvalidInputException {

		this.codigo = codigo;
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
		validaCpf();
		validaEmail();
		validaNome();
	}

	public Cliente(UUID codigo, String nome, String cpf, String email) throws InvalidInputException {

		this(codigo, nome, Cpf.fromString(cpf), Email.fromString(email));
	}

	public void validaCpf() throws InvalidInputException {

		if (cpf == null) {
			throw new InvalidInputException(CPF_AUSENTE);
		}
		cpf.validar();
	}

	public void validaEmail() throws InvalidInputException {

		if (email == null) {
			throw new InvalidInputException(EMAIL_AUSENTE);
		}
		email.validar();
	}

	public void validaNome() throws InvalidInputException {

		if (nome == null || nome.length() == 0) {
			throw new InvalidInputException(NOME_AUSENTE);
		}
	}

	public UUID getCodigo() {
		return codigo;
	}

	public String getNome() {
		return nome;
	}

	public Cpf getCpf() {
		return cpf;
	}

	public Email getEmail() {
		return email;
	}
}
 
