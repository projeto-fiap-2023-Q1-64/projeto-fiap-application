package br.fiap.projeto.contexto.produto.domain.port.service;

import br.fiap.projeto.contexto.produto.application.rest.dto.ProdutoDTO;
import br.fiap.projeto.contexto.produto.domain.enums.CategoriaProduto;

import java.util.List;
import java.util.UUID;

public interface ProdutoServicePort {

    List<ProdutoDTO> buscaTodos();

    ProdutoDTO buscaProduto(String codigo);

    List<ProdutoDTO> buscaProdutosPorCategoria(CategoriaProduto categoria);

    List<String> getCategoriasDeProdutos();

    ProdutoDTO criaProduto(ProdutoDTO produtoDTO);

    void removeProduto(String codigo);

    void atualizaProduto(String codigo, ProdutoDTO produto);

}
