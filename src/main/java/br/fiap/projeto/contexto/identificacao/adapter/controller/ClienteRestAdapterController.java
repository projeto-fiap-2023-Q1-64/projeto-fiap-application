package br.fiap.projeto.contexto.identificacao.adapter.controller;

import br.fiap.projeto.contexto.identificacao.adapter.controller.port.IClienteRestAdapterController;
import br.fiap.projeto.contexto.identificacao.adapter.controller.rest.request.ClienteRequestDTO;
import br.fiap.projeto.contexto.identificacao.adapter.controller.rest.response.ClienteResponseDTO;
import br.fiap.projeto.contexto.identificacao.entity.Cliente;
import br.fiap.projeto.contexto.identificacao.usecase.exception.EntidadeNaoEncontradaException;
import br.fiap.projeto.contexto.identificacao.usecase.exception.EntradaInvalidaException;
import br.fiap.projeto.contexto.identificacao.usecase.port.IGestaoClienteUsecase;

import java.util.List;
import java.util.stream.Collectors;

public class ClienteRestAdapterController implements IClienteRestAdapterController {

    private final IGestaoClienteUsecase gestaoClienteUsecase;

    public ClienteRestAdapterController(IGestaoClienteUsecase gestaoClienteUsecase) {
        this.gestaoClienteUsecase = gestaoClienteUsecase;
    }

    @Override
    public ClienteResponseDTO insere(ClienteRequestDTO cliente) throws EntradaInvalidaException {
        Cliente clienteSalvo = gestaoClienteUsecase.insere(new Cliente(cliente.getNome(), cliente.getCpf(), cliente.getEmail()));
        return ClienteResponseDTO.fromCliente(clienteSalvo);
    }

    @Override
    public ClienteResponseDTO atualiza(String codigo, ClienteRequestDTO cliente) throws EntidadeNaoEncontradaException, EntradaInvalidaException {
        Cliente clienteAtualizado = gestaoClienteUsecase.edita(new Cliente(codigo, cliente.getNome(), cliente.getCpf(), cliente.getEmail()));
        return ClienteResponseDTO.fromCliente(clienteAtualizado);
    }

    @Override
    public void remove(String codigo) throws EntidadeNaoEncontradaException, EntradaInvalidaException {
        gestaoClienteUsecase.remove(codigo);
    }

    @Override
    public ClienteResponseDTO busca(String codigo) throws EntidadeNaoEncontradaException, EntradaInvalidaException {
        Cliente clienteRecuperado = gestaoClienteUsecase.busca(codigo);
        return ClienteResponseDTO.fromCliente(clienteRecuperado);
    }

    @Override
    public List<ClienteResponseDTO> buscaTodos() {
        List<Cliente> clientes = gestaoClienteUsecase.buscaTodos();
        return clientes.stream().map(ClienteResponseDTO::fromCliente).collect(Collectors.toList());
    }

    @Override
    public ClienteResponseDTO buscaPorCpf(String cpf) throws EntidadeNaoEncontradaException {
        Cliente cliente = gestaoClienteUsecase.buscaPorCpf(cpf);
        return ClienteResponseDTO.fromCliente(cliente);
    }
}
