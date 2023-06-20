package br.fiap.projeto.contexto.produto.domain.port.repository;

import br.fiap.projeto.contexto.produto.domain.Produto;
import br.fiap.projeto.contexto.produto.domain.enums.CategoriaProduto;

import java.util.List;
import java.util.UUID;

public interface ProdutoRepository {

    List<Produto> buscaTodos();

    Produto buscaProduto(UUID codigo);

    Produto buscaProdutoPorCategoria(CategoriaProduto categoria);

    List<String> buscaCategoriasDeProdutos();

    void criaProduto(Produto produto);

    void removeProduto(UUID codigo);

    void atualizaProduto(Produto produto);

}
