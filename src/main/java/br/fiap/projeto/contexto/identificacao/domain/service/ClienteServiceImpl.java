package br.fiap.projeto.contexto.identificacao.domain.service;

import br.fiap.projeto.contexto.identificacao.domain.entity.Cliente;
import br.fiap.projeto.contexto.identificacao.domain.port.dto.ClienteDTO;
import br.fiap.projeto.contexto.identificacao.domain.port.repository.ClienteRepository;
import br.fiap.projeto.contexto.identificacao.domain.port.service.ClienteService;
import br.fiap.projeto.exception.EntityNotFoundException;
import lombok.SneakyThrows;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteServiceImpl(ClienteRepository clienteRepository) {

        this.clienteRepository = clienteRepository;
    }

    @Override @SneakyThrows
    public ClienteDTO busca(UUID codigo) {

        ClienteDTO cliente = ClienteDTO.fromCliente(clienteRepository.busca(codigo));
        if (Objects.isNull(cliente)) {
            throw new EntityNotFoundException("Cliente não encontrado!");
        }
        return cliente;
    }

    @Override
    public List<ClienteDTO> buscaTodos() {

        return clienteRepository.buscaTodos().stream()
                .map(ClienteDTO::fromCliente)
                .collect(Collectors.toList());
    }

    @Override @SneakyThrows
    public ClienteDTO insere(ClienteDTO clienteDTO) {

        Cliente cliente = clienteDTO.toCliente();
        return ClienteDTO.fromCliente(clienteRepository.insere(cliente));
    }

    @Override
    public ClienteDTO edita(ClienteDTO cliente) {

        return ClienteDTO.fromCliente(clienteRepository.edita(cliente.toCliente()));
    }

    @Override
    public void remove(UUID codigo) {

        clienteRepository.remove(codigo);
    }

    @Override
    public ClienteDTO buscaPorCpf(String cpf) {

        return clienteRepository.buscaPorCpf(cpf);
    }
}
