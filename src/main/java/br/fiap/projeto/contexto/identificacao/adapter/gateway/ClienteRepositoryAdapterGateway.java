package br.fiap.projeto.contexto.identificacao.adapter.gateway;

import br.fiap.projeto.contexto.identificacao.entity.Cliente;
import br.fiap.projeto.contexto.identificacao.external.repository.entity.ClienteEntity;
import br.fiap.projeto.contexto.identificacao.external.repository.postgres.SpringClienteRepository;
import br.fiap.projeto.contexto.identificacao.usecase.port.IClienteRepositoryAdapterGateway;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class ClienteRepositoryAdapterGateway implements IClienteRepositoryAdapterGateway {

    private final SpringClienteRepository springClienteRepository;

    public ClienteRepositoryAdapterGateway(SpringClienteRepository springClienteRepository) {
        this.springClienteRepository = springClienteRepository;
    }

    @Override
    public Cliente insere(Cliente cliente) {
        ClienteEntity clienteEntity;
        clienteEntity = ClienteEntity.fromCliente(cliente);
        clienteEntity = springClienteRepository.save(clienteEntity);

        return clienteEntity.toCliente();
    }

    @Override
    public Cliente atualiza(Cliente cliente) {
        ClienteEntity clienteEntity = ClienteEntity.fromCliente(cliente);
        clienteEntity = springClienteRepository.save(clienteEntity);
        return clienteEntity.toCliente();
    }

    @Override
    public void remove(String codigo) {
        Optional<ClienteEntity> existing;
        existing = springClienteRepository.findByCodigoAndDataExclusaoIsNull(codigo);
        if (existing.isPresent()) {
            existing.get().setDataExclusao(LocalDateTime.now());
            springClienteRepository.save(existing.get());
        }
    }

    @Override
    public Cliente busca(String codigo) {
        Optional<ClienteEntity> clienteRecuperado;
        clienteRecuperado = springClienteRepository.findByCodigoAndDataExclusaoIsNull(codigo);
        if (!clienteRecuperado.isPresent()) {
            return null;
        }

        return clienteRecuperado.get().toCliente();
    }

    @Override
    public List<Cliente> buscaTodos() {
        List<ClienteEntity> entidades;
        entidades = springClienteRepository.findAllByDataExclusaoIsNull();

        List<Cliente> clientes;
        clientes = entidades.stream().map(ClienteEntity::toCliente).collect(Collectors.toList());

        return clientes;
    }

    @Override
    public Cliente buscaPorCpf(String cpf) {
        ClienteEntity entity = springClienteRepository.findByCpfAndDataExclusaoIsNull(cpf);
        if (Objects.nonNull(entity)) {
            return entity.toCliente();
        }
        return null;
    }

    @Override
    public Cliente buscaPorEmail(String email) {
        ClienteEntity entity = springClienteRepository.findByEmailAndDataExclusaoIsNull(email);
        if (Objects.nonNull(entity)) {
            return entity.toCliente();
        }
        return null;
    }
}
