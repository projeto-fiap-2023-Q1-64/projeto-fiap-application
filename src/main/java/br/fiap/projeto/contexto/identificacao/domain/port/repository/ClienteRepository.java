package br.fiap.projeto.contexto.identificacao.domain.port.repository;

import br.fiap.projeto.contexto.identificacao.domain.entity.Cliente;
import br.fiap.projeto.contexto.identificacao.application.rest.response.ClienteResponseDTO;

import java.util.List;

public interface ClienteRepository {

    Cliente busca(String codigo);

    List<Cliente> buscaTodos();

    Cliente insere(Cliente cliente);

    Cliente edita(Cliente cliente);

    void remove(String codigo);

    ClienteResponseDTO buscaPorCpf(String cpf);
}
