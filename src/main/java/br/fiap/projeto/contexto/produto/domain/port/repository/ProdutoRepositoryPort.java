package br.fiap.projeto.contexto.produto.domain.port.repository;

import br.fiap.projeto.contexto.produto.domain.Produto;
import br.fiap.projeto.contexto.produto.domain.enums.CategoriaProduto;

import java.util.List;
import java.util.Optional;

public interface ProdutoRepositoryPort {

    List<Produto> buscaTodos();

    Optional<Produto> buscaProduto(String codigo);

    List<Produto> buscaProdutosPorCategoria(CategoriaProduto categoria);

    Produto criaProduto(Produto produto);

    void removeProduto(String codigo);

    void atualizaProduto(Produto produto);

}
