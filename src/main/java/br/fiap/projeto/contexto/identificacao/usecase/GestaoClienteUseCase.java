package br.fiap.projeto.contexto.identificacao.usecase;

import br.fiap.projeto.contexto.identificacao.entity.Cliente;
import br.fiap.projeto.contexto.identificacao.usecase.exception.EntidadeNaoEncontradaException;
import br.fiap.projeto.contexto.identificacao.usecase.exception.EntradaInvalidaException;
import br.fiap.projeto.contexto.identificacao.usecase.port.repository.IClienteRepositoryAdapterGateway;
import br.fiap.projeto.contexto.identificacao.usecase.port.service.IGestaoClienteUsecase;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static br.fiap.projeto.contexto.identificacao.entity.Cliente.*;

public class GestaoClienteUseCase implements IGestaoClienteUsecase {

    private final IClienteRepositoryAdapterGateway clienteRepositoryAdapterGateway;

    public GestaoClienteUseCase(IClienteRepositoryAdapterGateway clienteRepository) {
        this.clienteRepositoryAdapterGateway = clienteRepository;
    }

    @Override
    public Cliente busca(String codigo) throws EntidadeNaoEncontradaException, EntradaInvalidaException {
        if (codigo == null) {
            throw new EntradaInvalidaException(Cliente.CODIGO_AUSENTE);
        }

        Cliente cliente = clienteRepositoryAdapterGateway.busca(codigo);
        if (Objects.isNull(cliente)) {
            throw new EntidadeNaoEncontradaException("Cliente não encontrado!");
        }
        return cliente;
    }

    @Override
    public List<Cliente> buscaTodos() {
        return clienteRepositoryAdapterGateway.buscaTodos();
    }

    @Override
    public Cliente insere(Cliente clienteRef) throws EntradaInvalidaException {
        Cliente clienteExistente;
        clienteExistente = clienteRepositoryAdapterGateway.buscaPorCpf(clienteRef.getCpf().getNumero());
        if (Objects.nonNull(clienteExistente)) {
            throw new EntradaInvalidaException(CPF_DUPLICADO);
        }
        clienteExistente = clienteRepositoryAdapterGateway.buscaPorEmail(clienteRef.getEmail().getEndereco());
        if (Objects.nonNull(clienteExistente)) {
            throw new EntradaInvalidaException(EMAIL_DUPLICADO);
        }

        Cliente novoCliente = new Cliente(UUID.randomUUID().toString(), clienteRef.getNome(), clienteRef.getCpf().getNumero(), clienteRef.getEmail().getEndereco());
        return clienteRepositoryAdapterGateway.insere(novoCliente);
    }

    @Override
    public Cliente edita(Cliente clienteRef) throws EntidadeNaoEncontradaException, EntradaInvalidaException {
        if (clienteRef.getCodigo() == null) {
            throw new EntradaInvalidaException(Cliente.CODIGO_AUSENTE);
        }

        Cliente clienteExistente;
        clienteExistente = busca(clienteRef.getCodigo());
        if (clienteExistente == null) {
            throw new EntidadeNaoEncontradaException(ENTIDADE_NAO_ENCONTRADA);
        }

        Cliente clienteAAtualizar;
        clienteAAtualizar = new Cliente(clienteExistente.getCodigo(), clienteRef.getNome(), clienteRef.getCpf().getNumero(), clienteRef.getEmail().getEndereco());
        return clienteRepositoryAdapterGateway.edita(clienteAAtualizar);
    }

    @Override
    public void remove(String codigo) throws EntidadeNaoEncontradaException, EntradaInvalidaException {
        Cliente cliente = busca(codigo);
        clienteRepositoryAdapterGateway.remove(cliente.getCodigo());
    }

    @Override
    public Cliente buscaPorCpf(String cpf) throws EntidadeNaoEncontradaException {
        Cliente clienteEncontrado = clienteRepositoryAdapterGateway.buscaPorCpf(cpf);
        if (clienteEncontrado == null) {
            throw new EntidadeNaoEncontradaException(ENTIDADE_NAO_ENCONTRADA);
        }
        return clienteEncontrado;
    }
}
