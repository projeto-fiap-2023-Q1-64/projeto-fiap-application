package br.fiap.projeto.contexto.identificacao.domain.port.repository;

import br.fiap.projeto.contexto.identificacao.domain.entity.Cliente;

import java.util.List;

public interface ClienteRepository {

    Cliente busca(Long codigo);

    List<Cliente> buscaTodos();

    Cliente insere(Cliente cliente);

    Cliente edita(Cliente cliente);

    void remove(Long codigo);
}
