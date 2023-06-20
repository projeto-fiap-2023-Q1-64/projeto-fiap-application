package br.fiap.projeto.contexto.identificacao.infrastructure.entity;

import br.fiap.projeto.contexto.identificacao.domain.entity.Cliente;
import br.fiap.projeto.contexto.identificacao.domain.vo.Cpf;
import br.fiap.projeto.contexto.identificacao.domain.vo.Email;
import lombok.SneakyThrows;

import javax.persistence.*;
import java.util.UUID;


@Entity
@Table(name = "clientes")
public class ClienteEntity {

    @Id
    private UUID codigo;

    private String nome;

    private String cpf;

    private String email;

    public ClienteEntity() {
    }

    public ClienteEntity(UUID codigo, String nome, String cpf, String email) {

        this.codigo = codigo;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
    }

    public ClienteEntity(UUID codigo, String nome, Cpf cpf, Email email) {

        this(codigo, nome, cpf.getNumero(), email.getEndereco());
    }

    @SneakyThrows
    public Cliente toCliente() {

        return new Cliente(codigo, nome, cpf, email);
    }

    public static ClienteEntity fromCliente(Cliente cliente) {

        return new ClienteEntity(cliente.getCodigo(), cliente.getNome(), cliente.getCpf(), cliente.getEmail());
    }
}
