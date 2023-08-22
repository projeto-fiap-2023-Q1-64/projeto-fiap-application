package br.fiap.projeto.contexto.identificacao.adapter.gateway;

import br.fiap.projeto.contexto.identificacao.entity.entity.Cliente;
import br.fiap.projeto.contexto.identificacao.adapter.controller.rest.response.ClienteDTO;
import br.fiap.projeto.contexto.identificacao.external.repository.postgres.SpringDataClienteRepository;
import br.fiap.projeto.contexto.identificacao.usecase.port.repository.ClienteRepository;
import br.fiap.projeto.contexto.identificacao.external.repository.entity.ClienteEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteRepositoryAdapterGateway implements ClienteRepository {

    @Autowired
    private SpringDataClienteRepository repository;

    @Override
    public Cliente busca(String codigo) {

        Cliente ret;
        Optional<ClienteEntity> optClienteEntity;

        optClienteEntity = repository.findByCodigoAndDataExclusaoIsNull(codigo);
        if (!optClienteEntity.isPresent()) {
            return null;
        }
        ret = optClienteEntity.get().toCliente();
        return ret;
    }

    @Override
    public List<Cliente> buscaTodos() {

        List<ClienteEntity> entidades;
        List<Cliente> clientes;
        entidades = repository.findAllByDataExclusaoIsNull();
        clientes = entidades.stream()
                .map(ClienteEntity::toCliente)
                .collect(Collectors.toList());

        return clientes;
    }

    @Override
    public Cliente insere(Cliente cliente) {

        ClienteEntity clienteEntity;

        clienteEntity = ClienteEntity.fromCliente(cliente);
        clienteEntity = repository.save(clienteEntity);

        return clienteEntity.toCliente();
    }

    @Override
    public Cliente edita(Cliente cliente) {

        ClienteEntity clienteEntity = ClienteEntity.fromCliente(cliente);
        clienteEntity = repository.save(clienteEntity);
        return clienteEntity.toCliente();
    }

    @Override
    public void remove(String codigo) {

        Optional<ClienteEntity> existing;
        existing = repository.findByCodigoAndDataExclusaoIsNull(codigo);
        if (existing.isPresent()) {
            existing.get().setDataExclusao(LocalDateTime.now());
            repository.save(existing.get());
        }
    }

    @Override
    public ClienteDTO buscaPorCpf(String cpf) {

        ClienteEntity entity = repository.findByCpfAndDataExclusaoIsNull(cpf);
        if (Objects.nonNull(entity)) {
            return ClienteDTO.fromCliente(entity.toCliente());
        }
        return null;
    }

    @Override
    public ClienteDTO buscaPorEmail(String email) {

        ClienteEntity entity = repository.findByEmailAndDataExclusaoIsNull(email);
        if (Objects.nonNull(entity)) {
            return ClienteDTO.fromCliente(entity.toCliente());
        }
        return null;
    }
}
