package br.fiap.projeto.contexto.identificacao.domain.entity;

import br.fiap.projeto.contexto.identificacao.domain.vo.Cpf;
import br.fiap.projeto.contexto.identificacao.domain.vo.Email;
import br.fiap.projeto.exception.EntradaInvalidaException;

import java.util.UUID;

public class Cliente {

	public final static String CPF_AUSENTE = "Informe o cpf!";
	public final static String EMAIL_AUSENTE = "Informe o e-mail!";
	public final static String NOME_AUSENTE = "Informe o nome!";
	public final static String ENTIDADE_NAO_ENCONTRADA = "Cliente não encontrado!";
	public final static String ENTIDADE_DUPLICADA = "Esse cpf já está cadastrado!";
	public final static String CODIGO_AUSENTE = "Informe o código do cliente!";
 
	private final UUID codigo;
	 
	private final String nome;
	 
	private final Cpf cpf;
	 
	private final Email email;

	public Cliente(UUID codigo, String nome, Cpf cpf, Email email) throws EntradaInvalidaException {

		this.codigo = codigo;
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
		validaCodigo();
		validaCpf();
		validaEmail();
		validaNome();
	}

	public Cliente(UUID codigo, String nome, String cpf, String email) throws EntradaInvalidaException {

		this(codigo, nome, Cpf.fromString(cpf), Email.fromString(email));
	}

	private void validaCodigo() throws EntradaInvalidaException {

		if (codigo == null) {
			throw new EntradaInvalidaException(CODIGO_AUSENTE);
		}
	}

	private void validaCpf() throws EntradaInvalidaException {

		if (cpf == null) {
			throw new EntradaInvalidaException(CPF_AUSENTE);
		}
		cpf.validar();
	}

	private void validaEmail() throws EntradaInvalidaException {

		if (email == null) {
			throw new EntradaInvalidaException(EMAIL_AUSENTE);
		}
		email.validar();
	}

	private void validaNome() throws EntradaInvalidaException {

		if (nome == null || nome.length() == 0) {
			throw new EntradaInvalidaException(NOME_AUSENTE);
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
 
