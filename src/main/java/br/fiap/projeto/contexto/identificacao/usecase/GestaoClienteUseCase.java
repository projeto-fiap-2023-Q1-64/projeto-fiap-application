package br.fiap.projeto.contexto.identificacao.usecase;

import br.fiap.projeto.contexto.identificacao.entity.Cliente;
import br.fiap.projeto.contexto.identificacao.usecase.exception.EntidadeNaoEncontradaException;
import br.fiap.projeto.contexto.identificacao.usecase.exception.EntradaInvalidaException;
import br.fiap.projeto.contexto.identificacao.usecase.port.IClienteRepositoryAdapterGateway;
import br.fiap.projeto.contexto.identificacao.usecase.port.IGestaoClienteUsecase;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static br.fiap.projeto.contexto.identificacao.entity.Cliente.*;

public class GestaoClienteUseCase implements IGestaoClienteUsecase {

    private final IClienteRepositoryAdapterGateway clienteRepositoryAdapterGateway;

    public GestaoClienteUseCase(IClienteRepositoryAdapterGateway clienteRepository) {
        this.clienteRepositoryAdapterGateway = clienteRepository;
    }

    @Override
    public Cliente insere(Cliente clienteRef) throws EntradaInvalidaException, EntidadeNaoEncontradaException {
        Optional<Cliente> clienteExistente = clienteRepositoryAdapterGateway.buscaPorCpf(clienteRef.getCpf().getNumero());

        if(clienteExistente.isPresent()) {
            throw new EntradaInvalidaException(CPF_DUPLICADO);
        }

        clienteExistente = clienteRepositoryAdapterGateway.buscaPorEmail(clienteRef.getEmail().getEndereco());
        if (clienteExistente.isPresent()) {
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

        Cliente clienteExistente = busca(clienteRef.getCodigo());
        if (clienteExistente == null) {
            throw new EntidadeNaoEncontradaException(ENTIDADE_NAO_ENCONTRADA);
        }

        Cliente clienteAAtualizar = new Cliente(clienteExistente.getCodigo(), clienteRef.getNome(), clienteRef.getCpf().getNumero(), clienteRef.getEmail().getEndereco());
        return clienteRepositoryAdapterGateway.atualiza(clienteAAtualizar);
    }

    @Override
    public void remove(String codigo) throws EntidadeNaoEncontradaException, EntradaInvalidaException {
        Optional<Cliente> cliente = this.clienteRepositoryAdapterGateway.buscaPorCodigoEDataExclusaoNula(codigo);
        cliente.orElseThrow(() -> new EntidadeNaoEncontradaException("Cliente não encontrado!"));
        cliente.get().adicionaDataDeExclusao();
        clienteRepositoryAdapterGateway.remove(cliente.get());
    }

    @Override
    public Cliente busca(String codigo) throws EntidadeNaoEncontradaException, EntradaInvalidaException {
        if (codigo == null) {
            throw new EntradaInvalidaException(Cliente.CODIGO_AUSENTE);
        }

        Optional<Cliente> cliente = clienteRepositoryAdapterGateway.busca(codigo);
        cliente.orElseThrow(() -> new EntidadeNaoEncontradaException("Cliente não encontrado!"));
        return cliente.get();
    }

    @Override
    public List<Cliente> buscaTodos() {
        return clienteRepositoryAdapterGateway.buscaTodos();
    }

    @Override
    public Cliente buscaPorCpf(String cpf) throws EntidadeNaoEncontradaException {
        Optional<Cliente> clienteEncontrado = clienteRepositoryAdapterGateway.buscaPorCpf(cpf);
        clienteEncontrado.orElseThrow(() -> new EntidadeNaoEncontradaException(ENTIDADE_NAO_ENCONTRADA));
        return clienteEncontrado.get();
    }
}
