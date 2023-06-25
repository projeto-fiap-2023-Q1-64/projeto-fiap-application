package br.fiap.projeto.contexto.identificacao.domain.port.service;

import br.fiap.projeto.contexto.identificacao.domain.entity.Cliente;
import br.fiap.projeto.contexto.identificacao.domain.port.dto.ClienteDTO;

import java.util.List;
import java.util.UUID;

public interface ClienteService {

    ClienteDTO busca(UUID codigo);

    List<ClienteDTO> buscaTodos();

    ClienteDTO insere(ClienteDTO cliente);

    ClienteDTO edita(ClienteDTO cliente);

    void remove(UUID codigo);

    ClienteDTO buscaPorCpf(String cpf);
}
