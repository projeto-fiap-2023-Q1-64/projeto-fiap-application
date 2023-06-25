package br.fiap.projeto.contexto.identificacao.domain.entity;

import br.fiap.projeto.contexto.identificacao.domain.vo.Cpf;
import br.fiap.projeto.contexto.identificacao.domain.vo.Email;
import br.fiap.projeto.exception.InvalidInputException;

import java.util.UUID;

public class Cliente {
 
	private UUID codigo;
	 
	private String nome;
	 
	private Cpf cpf;
	 
	private Email email;

	public Cliente(UUID codigo, String nome, Cpf cpf, Email email) throws InvalidInputException {

		cpf.validar();
		email.validar();
		this.codigo = codigo;
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
	}

	public Cliente(UUID codigo, String nome, String cpf, String email) throws InvalidInputException {

		this(codigo, nome, Cpf.fromString(cpf), Email.fromString(email));
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
 
