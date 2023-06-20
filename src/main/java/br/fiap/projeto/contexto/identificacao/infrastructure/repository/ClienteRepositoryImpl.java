package br.fiap.projeto.contexto.identificacao.infrastructure.repository;

import br.fiap.projeto.contexto.identificacao.domain.entity.Cliente;
import br.fiap.projeto.contexto.identificacao.domain.port.repository.ClienteRepository;
import br.fiap.projeto.contexto.identificacao.infrastructure.entity.ClienteEntity;
import br.fiap.projeto.contexto.identificacao.infrastructure.repository.SpringDataClienteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteRepositoryImpl implements ClienteRepository {

    private SpringDataClienteRepository repository;
    private ModelMapper mapper;

    @Override
    public Cliente busca(Long codigo) {

        Cliente ret;
        Optional<ClienteEntity> optClienteEntity;

        optClienteEntity = repository.findById(codigo);
        if (!optClienteEntity.isPresent()) {
            return null;
        }
        ret = mapper.map(optClienteEntity.get(), Cliente.class);
        return ret;
    }

    @Override
    public List<Cliente> buscaTodos() {

        List<ClienteEntity> entidades;
        List<Cliente> clientes;
        entidades = repository.findAll();
        clientes = entidades.stream()
                .map(cli -> mapper.map(cli, Cliente.class))
                .collect(Collectors.toList());

        return clientes;
    }

    @Override
    public Cliente insere(Cliente cliente) {

        ClienteEntity clienteEntity;

        clienteEntity = mapper.map(cliente, ClienteEntity.class);
        clienteEntity = repository.save(clienteEntity);

        return mapper.map(clienteEntity, Cliente.class);
    }

    @Override
    public Cliente edita(Cliente cliente) {

        ClienteEntity clienteEntity = mapper.map(cliente, ClienteEntity.class);
        clienteEntity = repository.save(clienteEntity);
        return mapper.map(clienteEntity, Cliente.class);
    }

    @Override
    public void remove(Long codigo) {

        repository.deleteById(codigo);
    }
}
