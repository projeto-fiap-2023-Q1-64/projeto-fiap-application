package br.fiap.projeto.contexto.produto.usecase.port;

import br.fiap.projeto.contexto.produto.entity.Produto;
import br.fiap.projeto.contexto.produto.entity.enums.CategoriaProduto;
import br.fiap.projeto.contexto.produto.usecase.exception.EntradaInvalidaException;
import br.fiap.projeto.contexto.produto.usecase.exception.ProdutoNaoEncontradoException;

import java.util.List;

public interface IGestaoProdutoUseCase {

    List<Produto> buscaTodos();

    Produto buscaProduto(String codigo) throws ProdutoNaoEncontradoException;

    List<Produto> buscaProdutosPorCategoria(CategoriaProduto categoria);

    List<String> getCategoriasDeProdutos();

    Produto criaProduto(Produto produto) throws EntradaInvalidaException;

    Void removeProduto(String codigo) throws ProdutoNaoEncontradoException;

    void atualizaProduto(String codigo, Produto produto) throws ProdutoNaoEncontradoException;

}
