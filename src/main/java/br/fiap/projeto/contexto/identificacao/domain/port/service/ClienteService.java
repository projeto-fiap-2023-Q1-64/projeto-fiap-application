package br.fiap.projeto.contexto.identificacao.domain.port.service;

import br.fiap.projeto.contexto.identificacao.application.rest.request.ClienteRequestDTO;
import br.fiap.projeto.contexto.identificacao.application.rest.response.ClienteDTO;
import br.fiap.projeto.contexto.identificacao.domain.entity.Cliente;

import java.util.List;

public interface ClienteService {

    Cliente busca(String codigo);

    List<Cliente> buscaTodos();

    Cliente insere(ClienteRequestDTO cliente);

    Cliente edita(Cliente cliente);

    void remove(String codigo);

    Cliente buscaPorCpf(String cpf);
}
