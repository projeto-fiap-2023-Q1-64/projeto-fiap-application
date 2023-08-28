package br.fiap.projeto.contexto.identificacao.domain.port.service;

import br.fiap.projeto.contexto.identificacao.application.rest.request.ClienteRequestDTO;
import br.fiap.projeto.contexto.identificacao.application.rest.response.ClienteDTO;
import br.fiap.projeto.contexto.identificacao.domain.entity.Cliente;
import br.fiap.projeto.contexto.identificacao.domain.exception.EntidadeNaoEncontradaException;
import br.fiap.projeto.contexto.identificacao.domain.exception.EntradaInvalidaException;

import java.util.List;

public interface ClienteService {

    Cliente busca(String codigo) throws EntidadeNaoEncontradaException, EntradaInvalidaException;

    List<Cliente> buscaTodos();

    Cliente insere(ClienteRequestDTO cliente) throws EntradaInvalidaException;

    Cliente edita(Cliente cliente) throws EntidadeNaoEncontradaException, EntradaInvalidaException;

    void remove(String codigo) throws EntidadeNaoEncontradaException, EntradaInvalidaException;

    Cliente buscaPorCpf(String cpf) throws EntidadeNaoEncontradaException;
}
