package br.fiap.projeto.contexto.identificacao.domain.port.repository;

import br.fiap.projeto.contexto.identificacao.application.rest.response.ClienteDTO;
import br.fiap.projeto.contexto.identificacao.domain.entity.Cliente;

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
