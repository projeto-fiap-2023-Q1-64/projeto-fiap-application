package br.fiap.projeto.contexto.identificacao.infrastructure.repository;

import br.fiap.projeto.contexto.identificacao.domain.entity.Cliente;
import br.fiap.projeto.contexto.identificacao.domain.port.dto.ClienteDTO;
import br.fiap.projeto.contexto.identificacao.domain.port.repository.ClienteRepository;
import br.fiap.projeto.contexto.identificacao.infrastructure.entity.ClienteEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ClienteRepositoryImpl implements ClienteRepository {

    @Autowired
    private SpringDataClienteRepository repository;

    @Override
    public Cliente busca(UUID codigo) {

        Cliente ret;
        Optional<ClienteEntity> optClienteEntity;

        optClienteEntity = repository.findById(codigo);
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
        entidades = repository.findAll();
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
    public void remove(UUID codigo) {

        repository.deleteById(codigo);
    }

    @Override
    public ClienteDTO buscaPorCpf(String cpf) {

        ClienteEntity entity = repository.findByCpf(cpf);
        if (Objects.nonNull(entity)) {
            return ClienteDTO.fromCliente(entity.toCliente());
        }
        return null;
    }
}
