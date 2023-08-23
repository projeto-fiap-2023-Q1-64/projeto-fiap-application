package br.fiap.projeto.contexto.identificacao.usecase.port;

import br.fiap.projeto.contexto.identificacao.entity.Cliente;
import br.fiap.projeto.contexto.identificacao.usecase.exception.EntidadeNaoEncontradaException;
import br.fiap.projeto.contexto.identificacao.usecase.exception.EntradaInvalidaException;

import java.util.List;

public interface IGestaoClienteUsecase {

    Cliente busca(String codigo) throws EntidadeNaoEncontradaException, EntradaInvalidaException;

    List<Cliente> buscaTodos();

    Cliente insere(Cliente cliente) throws EntradaInvalidaException;

    Cliente edita(Cliente cliente) throws EntidadeNaoEncontradaException, EntradaInvalidaException;

    void remove(String codigo) throws EntidadeNaoEncontradaException, EntradaInvalidaException;

    Cliente buscaPorCpf(String cpf) throws EntidadeNaoEncontradaException;
}
