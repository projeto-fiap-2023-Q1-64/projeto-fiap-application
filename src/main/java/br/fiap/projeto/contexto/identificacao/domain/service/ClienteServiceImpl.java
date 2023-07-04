package br.fiap.projeto.contexto.identificacao.domain.service;

import br.fiap.projeto.contexto.identificacao.application.rest.request.ClienteRequestDTO;
import br.fiap.projeto.contexto.identificacao.domain.entity.Cliente;
import br.fiap.projeto.contexto.identificacao.application.rest.response.ClienteDTO;
import br.fiap.projeto.contexto.identificacao.domain.port.repository.ClienteRepository;
import br.fiap.projeto.contexto.identificacao.domain.port.service.ClienteService;
import br.fiap.projeto.contexto.identificacao.infrastructure.exception.EntidadeNaoEncontradaException;
import br.fiap.projeto.contexto.identificacao.infrastructure.exception.EntradaInvalidaException;
import lombok.SneakyThrows;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

import static br.fiap.projeto.contexto.identificacao.domain.entity.Cliente.*;

public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteServiceImpl(ClienteRepository clienteRepository) {

        this.clienteRepository = clienteRepository;
    }

    @Override @SneakyThrows
    public ClienteDTO busca(String codigo) {

        if (codigo == null) {
            throw new EntradaInvalidaException(Cliente.CODIGO_AUSENTE);
        }

        ClienteDTO cliente = ClienteDTO.fromCliente(clienteRepository.busca(codigo));
        if (Objects.isNull(cliente)) {
            throw new EntidadeNaoEncontradaException("Cliente não encontrado!");
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
    public ClienteDTO insere(ClienteRequestDTO ref) {

        Cliente cliente;
        ClienteDTO clienteDTO;
        ClienteDTO existente;

        clienteDTO = new ClienteDTO(UUID.randomUUID().toString(), ref.getNome(), ref.getCpf(), ref.getEmail());
        existente = clienteRepository.buscaPorCpf(clienteDTO.getCpf());
        if (Objects.nonNull(existente)) {
            throw new EntradaInvalidaException(CPF_DUPLICADO);
        }
        existente = clienteRepository.buscaPorEmail(clienteDTO.getEmail());
        if (Objects.nonNull(existente)) {
            throw new EntradaInvalidaException(EMAIL_DUPLICADO);
        }
        cliente = clienteDTO.toCliente();
        return ClienteDTO.fromCliente(clienteRepository.insere(cliente));
    }

    @Override @SneakyThrows
    public ClienteDTO edita(ClienteDTO clienteDTO) {

        ClienteDTO existente;
        Cliente cliente;
        if (clienteDTO.getCodigo() == null) {
            throw new EntradaInvalidaException(Cliente.CODIGO_AUSENTE);
        }
        existente = busca(clienteDTO.getCodigo());
        if (existente == null) {
            throw new EntidadeNaoEncontradaException(ENTIDADE_NAO_ENCONTRADA);
        }
        cliente = new Cliente(existente.getCodigo(), clienteDTO.getNome(), clienteDTO.getCpf(), clienteDTO.getEmail());
        return ClienteDTO.fromCliente(clienteRepository.edita(cliente));
    }

    @Override
    public void remove(String codigo) {

        ClienteDTO clienteDTO = busca(codigo);
        clienteRepository.remove(clienteDTO.getCodigo());
    }

    @Override @SneakyThrows
    public ClienteDTO buscaPorCpf(String cpf) {

        ClienteDTO ret = clienteRepository.buscaPorCpf(cpf);
        if (ret == null) {
            throw new EntidadeNaoEncontradaException(ENTIDADE_NAO_ENCONTRADA);
        }
        return ret;
    }
}
