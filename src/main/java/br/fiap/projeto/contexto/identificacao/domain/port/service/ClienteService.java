package br.fiap.projeto.contexto.identificacao.domain.port.service;

import br.fiap.projeto.contexto.identificacao.application.rest.request.ClienteRequestDTO;
import br.fiap.projeto.contexto.identificacao.application.rest.response.ClienteResponseDTO;

import java.util.List;

public interface ClienteService {

    ClienteResponseDTO busca(String codigo);

    List<ClienteResponseDTO> buscaTodos();

    ClienteResponseDTO insere(ClienteRequestDTO cliente);

    ClienteResponseDTO edita(ClienteResponseDTO cliente);

    void remove(String codigo);

    ClienteResponseDTO buscaPorCpf(String cpf);
}
