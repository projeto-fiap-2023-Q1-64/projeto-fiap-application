package br.fiap.projeto.contexto.identificacao.domain.service;

import br.fiap.projeto.contexto.identificacao.application.rest.request.ClienteRequestDTO;
import br.fiap.projeto.contexto.identificacao.domain.entity.Cliente;
import br.fiap.projeto.contexto.identificacao.application.rest.response.ClienteResponseDTO;
import br.fiap.projeto.contexto.identificacao.domain.port.repository.ClienteRepository;
import br.fiap.projeto.contexto.identificacao.domain.port.service.ClienteService;
import br.fiap.projeto.contexto.identificacao.infrastructure.exception.EntidadeNaoEncontradaException;
import br.fiap.projeto.contexto.identificacao.infrastructure.exception.EntradaInvalidaException;
import lombok.SneakyThrows;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

import static br.fiap.projeto.contexto.identificacao.domain.entity.Cliente.ENTIDADE_DUPLICADA;
import static br.fiap.projeto.contexto.identificacao.domain.entity.Cliente.ENTIDADE_NAO_ENCONTRADA;

public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteServiceImpl(ClienteRepository clienteRepository) {

        this.clienteRepository = clienteRepository;
    }

    @Override @SneakyThrows
    public ClienteResponseDTO busca(String codigo) {

        if (codigo == null) {
            throw new EntradaInvalidaException(Cliente.CODIGO_AUSENTE);
        }

        ClienteResponseDTO cliente = ClienteResponseDTO.fromCliente(clienteRepository.busca(codigo));
        if (Objects.isNull(cliente)) {
            throw new EntidadeNaoEncontradaException("Cliente não encontrado!");
        }
        return cliente;
    }

    @Override
    public List<ClienteResponseDTO> buscaTodos() {

        return clienteRepository.buscaTodos().stream()
                .map(ClienteResponseDTO::fromCliente)
                .collect(Collectors.toList());
    }

    @Override @SneakyThrows
    public ClienteResponseDTO insere(ClienteRequestDTO ref) {

        Cliente cliente;
        ClienteResponseDTO clienteResponseDTO;
        ClienteResponseDTO existente;

        clienteResponseDTO = new ClienteResponseDTO(UUID.randomUUID().toString(), ref.getNome(), ref.getCpf(), ref.getEmail());
        cliente = clienteResponseDTO.toCliente();
        try {
            existente = buscaPorCpf(cliente.getCpf().getNumero());
            if (existente != null) {
                throw new EntradaInvalidaException(ENTIDADE_DUPLICADA);
            }
        } catch (Exception ex) {
            if (!(ex instanceof EntidadeNaoEncontradaException)) {
                throw ex;
            }
        }
        return ClienteResponseDTO.fromCliente(clienteRepository.insere(cliente));
    }

    @Override @SneakyThrows
    public ClienteResponseDTO edita(ClienteResponseDTO clienteResponseDTO) {

        ClienteResponseDTO existente;
        Cliente cliente;
        if (clienteResponseDTO.getCodigo() == null) {
            throw new EntradaInvalidaException(Cliente.CODIGO_AUSENTE);
        }
        existente = busca(clienteResponseDTO.getCodigo());
        if (existente == null) {
            throw new EntidadeNaoEncontradaException(ENTIDADE_NAO_ENCONTRADA);
        }
        cliente = new Cliente(existente.getCodigo(), clienteResponseDTO.getNome(), clienteResponseDTO.getCpf(), clienteResponseDTO.getEmail());
        return ClienteResponseDTO.fromCliente(clienteRepository.edita(cliente));
    }

    @Override
    public void remove(String codigo) {

        ClienteResponseDTO clienteResponseDTO = busca(codigo);
        clienteRepository.remove(clienteResponseDTO.getCodigo());
    }

    @Override @SneakyThrows
    public ClienteResponseDTO buscaPorCpf(String cpf) {

        ClienteResponseDTO ret = clienteRepository.buscaPorCpf(cpf);
        if (ret == null) {
            throw new EntidadeNaoEncontradaException(ENTIDADE_NAO_ENCONTRADA);
        }
        return ret;
    }
}
