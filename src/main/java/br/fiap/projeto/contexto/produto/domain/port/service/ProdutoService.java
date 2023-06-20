package br.fiap.projeto.contexto.produto.domain.port.service;

import br.fiap.projeto.contexto.produto.domain.dto.ProdutoDTO;
import br.fiap.projeto.contexto.produto.domain.enums.CategoriaProduto;

import java.util.List;
import java.util.UUID;

public interface ProdutoService {

    List<ProdutoDTO> buscaTodos();

    ProdutoDTO buscaProduto(UUID codigo);

    List<ProdutoDTO> buscaProdutoPorCategoria(CategoriaProduto categoria);

    List<String> getCategoriasDeProdutos();

    void criaProduto(ProdutoDTO produto);

    void removeProduto(UUID codigo);

    void atualizaProduto(ProdutoDTO produto);

}
