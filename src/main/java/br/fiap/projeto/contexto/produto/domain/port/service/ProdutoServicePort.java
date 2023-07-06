package br.fiap.projeto.contexto.produto.domain.port.service;

import br.fiap.projeto.contexto.produto.domain.Produto;
import br.fiap.projeto.contexto.produto.domain.enums.CategoriaProduto;
import br.fiap.projeto.contexto.produto.domain.exception.EntradaInvalidaException;
import br.fiap.projeto.contexto.produto.domain.exception.ProdutoNaoEncontradoException;

import java.util.List;

public interface ProdutoServicePort {

    List<Produto> buscaTodos();

    Produto buscaProduto(String codigo) throws ProdutoNaoEncontradoException;

    List<Produto> buscaProdutosPorCategoria(CategoriaProduto categoria);

    List<String> getCategoriasDeProdutos();

    Produto criaProduto(Produto produto) throws EntradaInvalidaException;

    void removeProduto(String codigo) throws ProdutoNaoEncontradoException;

    void atualizaProduto(String codigo, Produto produto) throws ProdutoNaoEncontradoException;

}
