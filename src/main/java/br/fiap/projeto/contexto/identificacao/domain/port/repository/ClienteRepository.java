package br.fiap.projeto.contexto.identificacao.domain.port.repository;

import br.fiap.projeto.contexto.identificacao.domain.entity.Cliente;
import br.fiap.projeto.contexto.identificacao.domain.port.dto.ClienteDTO;

import java.util.List;
import java.util.UUID;

public interface ClienteRepository {

    Cliente busca(UUID codigo);

    List<Cliente> buscaTodos();

    Cliente insere(Cliente cliente);

    Cliente edita(Cliente cliente);

    void remove(UUID codigo);

    ClienteDTO buscaPorCpf(String cpf);
}
