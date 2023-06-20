package br.fiap.projeto.contexto.identificacao.domain.port.service;

import br.fiap.projeto.contexto.identificacao.domain.entity.Cliente;
import br.fiap.projeto.contexto.identificacao.domain.port.dto.ClienteDTO;

import java.util.List;

public interface ClienteService {

    ClienteDTO busca(Long codigo);

    List<ClienteDTO> buscaTodos();

    ClienteDTO insere(ClienteDTO cliente);

    ClienteDTO edita(ClienteDTO cliente);

    void remove(Long codigo);
}
