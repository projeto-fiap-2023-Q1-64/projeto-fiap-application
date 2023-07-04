package br.fiap.projeto.contexto.identificacao.domain.port.service;

import br.fiap.projeto.contexto.identificacao.application.rest.request.ClienteRequestDTO;
import br.fiap.projeto.contexto.identificacao.application.rest.response.ClienteDTO;

import java.util.List;

public interface ClienteService {

    ClienteDTO busca(String codigo);

    List<ClienteDTO> buscaTodos();

    ClienteDTO insere(ClienteRequestDTO cliente);

    ClienteDTO edita(ClienteDTO cliente);

    void remove(String codigo);

    ClienteDTO buscaPorCpf(String cpf);
}
