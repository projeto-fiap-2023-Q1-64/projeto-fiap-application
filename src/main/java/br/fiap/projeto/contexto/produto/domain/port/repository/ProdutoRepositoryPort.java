package br.fiap.projeto.contexto.produto.domain.port.repository;

import br.fiap.projeto.contexto.produto.domain.Produto;
import br.fiap.projeto.contexto.produto.domain.enums.CategoriaProduto;

import java.util.List;

public interface ProdutoRepositoryPort {

    List<Produto> buscaTodos();

    Produto buscaProduto(String codigo);

    List<Produto> buscaProdutosPorCategoria(CategoriaProduto categoria);

    List<String> buscaCategoriasDeProdutos();

    Produto criaProduto(Produto produto);

    void removeProduto(String codigo);

    void atualizaProduto(String codigo, Produto produto);

}
