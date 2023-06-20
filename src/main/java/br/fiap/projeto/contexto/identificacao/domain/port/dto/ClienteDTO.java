package br.fiap.projeto.contexto.identificacao.domain.port.dto;

import br.fiap.projeto.contexto.identificacao.domain.entity.Cliente;
import br.fiap.projeto.contexto.identificacao.domain.vo.Cpf;
import br.fiap.projeto.contexto.identificacao.domain.vo.Email;

public class ClienteDTO {

    private Long codigo;

    private String nome;

    private Cpf cpf;

    private Email email;

    public ClienteDTO(Long codigo, String nome, Cpf cpf, Email email) {
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

    public Cliente toCliente() {

        return new Cliente(codigo, nome, cpf, email);
    }

    public static ClienteDTO fromCliente(Cliente cliente) {

        if (cliente == null) {
            return null;
        }
        return new ClienteDTO(cliente.getCodigo(), cliente.getNome(), cliente.getCpf(), cliente.getEmail());
    }

    public static Cliente toCliente(ClienteDTO cliente) {

        return new Cliente(cliente.getCodigo(), cliente.getNome(), cliente.getCpf(), cliente.getEmail());
    }
}
