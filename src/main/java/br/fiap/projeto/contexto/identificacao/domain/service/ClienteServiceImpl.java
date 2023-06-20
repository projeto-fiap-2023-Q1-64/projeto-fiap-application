package br.fiap.projeto.contexto.identificacao.domain.service;

import br.fiap.projeto.contexto.identificacao.domain.port.dto.ClienteDTO;
import br.fiap.projeto.contexto.identificacao.domain.port.repository.ClienteRepository;
import br.fiap.projeto.contexto.identificacao.domain.port.service.ClienteService;

import java.util.List;
import java.util.stream.Collectors;

public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteServiceImpl(ClienteRepository clienteRepository) {

        this.clienteRepository = clienteRepository;
    }

    @Override
    public ClienteDTO busca(Long codigo) {

        return ClienteDTO.fromCliente(clienteRepository.busca(codigo));
    }

    @Override
    public List<ClienteDTO> buscaTodos() {

        return clienteRepository.buscaTodos().stream()
                .map(ClienteDTO::fromCliente)
                .collect(Collectors.toList());
    }

    @Override
    public ClienteDTO insere(ClienteDTO cliente) {

        return ClienteDTO.fromCliente(clienteRepository.insere(cliente.toCliente()));
    }

    @Override
    public ClienteDTO edita(ClienteDTO cliente) {

        return ClienteDTO.fromCliente(clienteRepository.edita(cliente.toCliente()));
    }

    @Override
    public void remove(Long codigo) {

        clienteRepository.remove(codigo);
    }
}
