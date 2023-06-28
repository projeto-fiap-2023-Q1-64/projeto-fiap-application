package br.fiap.projeto.contexto.identificacao.infrastructure.entity;

import br.fiap.projeto.contexto.identificacao.domain.entity.Cliente;
import br.fiap.projeto.contexto.identificacao.domain.vo.Cpf;
import br.fiap.projeto.contexto.identificacao.domain.vo.Email;
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

    public ClienteEntity(String codigo, String nome, String cpf, String email) {

        this.codigo = codigo;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
    }

    public ClienteEntity(String codigo, String nome, Cpf cpf, Email email) {

        this(codigo, nome, cpf.getNumero(), email.getEndereco());
    }

    @SneakyThrows
    public Cliente toCliente() {

        return new Cliente(
                codigo,
                nome,
                cpf,
                email
        );
    }

    public static ClienteEntity fromCliente(Cliente cliente) {

        return new ClienteEntity(
                cliente.getCodigo(),
                cliente.getNome(),
                cliente.getCpf(),
                cliente.getEmail()
        );
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

    public void setDataExclusao(LocalDateTime dataExclusao) {
        this.dataExclusao = dataExclusao;
    }
}
