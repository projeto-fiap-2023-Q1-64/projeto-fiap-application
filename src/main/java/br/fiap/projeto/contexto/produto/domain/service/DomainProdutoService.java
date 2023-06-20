package br.fiap.projeto.contexto.produto.domain.service;

import br.fiap.projeto.contexto.produto.domain.Produto;
import br.fiap.projeto.contexto.produto.domain.dto.ProdutoDTO;
import br.fiap.projeto.contexto.produto.domain.enums.CategoriaProduto;
import br.fiap.projeto.contexto.produto.domain.port.repository.ProdutoRepository;
import br.fiap.projeto.contexto.produto.domain.port.service.ProdutoService;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class DomainProdutoService implements ProdutoService {

    private final ProdutoRepository produtoRepository;

    public DomainProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Override
    public List<ProdutoDTO> buscaTodos() {
        List<Produto> list = produtoRepository.buscaTodos();
        return list.stream().map(Produto::toProdutoDTO).collect(Collectors.toList());
    }

    @Override
    public ProdutoDTO buscaProduto(UUID codigo) {
        return null;
    }

    @Override
    public List<ProdutoDTO> buscaProdutoPorCategoria(CategoriaProduto categoria) {
        return null;
    }

    @Override
    public List<String> getCategoriasDeProdutos() {
        return null;
    }

    @Override
    public void criaProduto(ProdutoDTO produto) {

    }

    @Override
    public void removeProduto(UUID codigo) {

    }

    @Override
    public void atualizaProduto(ProdutoDTO produto) {

    }
}
