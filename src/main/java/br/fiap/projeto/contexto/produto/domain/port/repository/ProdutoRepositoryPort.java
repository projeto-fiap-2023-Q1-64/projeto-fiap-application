package br.fiap.projeto.contexto.produto.domain.port.repository;

import br.fiap.projeto.contexto.produto.domain.Produto;
import br.fiap.projeto.contexto.produto.domain.enums.CategoriaProduto;

import java.util.List;
import java.util.UUID;

public interface ProdutoRepositoryPort {

    List<Produto> buscaTodos();

    Produto buscaProduto(UUID codigo);

    List<Produto> buscaProdutosPorCategoria(CategoriaProduto categoria);

    List<String> buscaCategoriasDeProdutos();

    Produto criaProduto(Produto produto);

    void removeProduto(UUID codigo);

    void atualizaProduto(UUID codigo, Produto produto);

}
