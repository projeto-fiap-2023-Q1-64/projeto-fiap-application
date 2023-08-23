package br.fiap.projeto.contexto.identificacao.adapter.controller.rest.response;

import br.fiap.projeto.contexto.identificacao.entity.Cliente;
import br.fiap.projeto.contexto.identificacao.entity.vo.Cpf;
import br.fiap.projeto.contexto.identificacao.entity.vo.Email;
import lombok.SneakyThrows;

import java.util.Optional;

public class ClienteResponseDTO {

    private String codigo;

    private String nome;

    private String cpf;

    private String email;

    public ClienteResponseDTO() {
    }

    public ClienteResponseDTO(String codigo, String nome, String cpf, String email) {
        this.codigo = codigo;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
    }

    public ClienteResponseDTO(String nome, String cpf, String email) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
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

    @SneakyThrows
    public Cliente toCliente() {

        return new Cliente(codigo, nome, cpf, email);
    }

    public static ClienteResponseDTO fromCliente(Cliente cliente) {

        if (cliente == null) {
            return null;
        }
        return new ClienteResponseDTO(
                cliente.getCodigo(),
                cliente.getNome(),
                Optional.ofNullable(cliente.getCpf()).map(Cpf::getNumero).orElse(null),
                Optional.ofNullable(cliente.getEmail()).map(Email::getEndereco).orElse(null)
        );
    }
}
