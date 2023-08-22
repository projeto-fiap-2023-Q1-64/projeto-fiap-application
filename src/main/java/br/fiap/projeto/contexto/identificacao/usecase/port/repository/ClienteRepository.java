package br.fiap.projeto.contexto.identificacao.usecase.port.repository;

import br.fiap.projeto.contexto.identificacao.entity.entity.Cliente;
import br.fiap.projeto.contexto.identificacao.adapter.controller.rest.response.ClienteDTO;

import java.util.List;

public interface ClienteRepository {

    Cliente busca(String codigo);

    List<Cliente> buscaTodos();

    Cliente insere(Cliente cliente);

    Cliente edita(Cliente cliente);

    void remove(String codigo);

    ClienteDTO buscaPorCpf(String cpf);

    ClienteDTO buscaPorEmail(String email);
}
