package br.fiap.projeto.contexto.identificacao.external.repository.entity;

import br.fiap.projeto.contexto.identificacao.entity.Cliente;
import br.fiap.projeto.contexto.identificacao.entity.vo.Cpf;
import br.fiap.projeto.contexto.identificacao.entity.vo.Email;
import lombok.SneakyThrows;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.time.LocalDateTime;


@Entity
@Table(name = "clientes", uniqueConstraints = @UniqueConstraint(name = "UN_CLIENTE", columnNames = {"codigo"}))
public class ClienteEntity {

    @Id
    private String codigo;
    private String nome;
    private String cpf;
    private String email;
    private LocalDateTime dataExclusao;

    public ClienteEntity() {
    }

    public ClienteEntity(String codigo, String nome, String cpf, String email, LocalDateTime dataExclusao) {
        this.codigo = codigo;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.dataExclusao = dataExclusao;
    }

    public ClienteEntity(String codigo, String nome, Cpf cpf, Email email, LocalDateTime dataExclusao) {
        this(codigo, nome, cpf.getNumero(), email.getEndereco(), dataExclusao);
    }

    public static ClienteEntity fromCliente(Cliente cliente) {
        return new ClienteEntity(cliente.getCodigo(), cliente.getNome(), cliente.getCpf(), cliente.getEmail(), cliente.getDataExclusao());
    }

    @SneakyThrows
    public Cliente toCliente() {
        return new Cliente(codigo, nome, cpf, email, dataExclusao);
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }

    public LocalDateTime getDataExclusao() {
        return dataExclusao;
    }

}
