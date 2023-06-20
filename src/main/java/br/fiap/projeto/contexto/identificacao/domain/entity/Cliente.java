package br.fiap.projeto.contexto.identificacao.domain.entity;

import br.fiap.projeto.contexto.identificacao.domain.vo.Cpf;
import br.fiap.projeto.contexto.identificacao.domain.vo.Email;

public class Cliente {
 
	private Long codigo;
	 
	private String nome;
	 
	private Cpf cpf;
	 
	private Email email;

	public Cliente(Long codigo, String nome, Cpf cpf, Email email) {
		this.codigo = codigo;
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
	}

	public Long getCodigo() {
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
 
