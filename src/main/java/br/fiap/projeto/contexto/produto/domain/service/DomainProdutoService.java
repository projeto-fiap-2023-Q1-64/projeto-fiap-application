package br.fiap.projeto.contexto.produto.domain.service;

import br.fiap.projeto.contexto.produto.domain.Produto;
import br.fiap.projeto.contexto.produto.domain.dto.ProdutoDTO;
import br.fiap.projeto.contexto.produto.domain.enums.CategoriaProduto;
import br.fiap.projeto.contexto.produto.domain.port.repository.ProdutoRepositoryPort;
import br.fiap.projeto.contexto.produto.domain.port.service.ProdutoServicePort;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class DomainProdutoService implements ProdutoServicePort {

    private final ProdutoRepositoryPort produtoRepositoryPort;

    public DomainProdutoService(ProdutoRepositoryPort produtoRepositoryPort) {
        this.produtoRepositoryPort = produtoRepositoryPort;
    }

    @Override
    public List<ProdutoDTO> buscaTodos() {
        List<Produto> produtos = produtoRepositoryPort.buscaTodos();
        return produtos.stream().map(Produto::toProdutoDTO).collect(Collectors.toList());
    }

    @Override
    public ProdutoDTO buscaProduto(UUID codigo) {
        Produto produto = produtoRepositoryPort.buscaProduto(codigo);
        return produto.toProdutoDTO();
    }

    @Override
    public List<ProdutoDTO> buscaProdutosPorCategoria(CategoriaProduto categoria) {
        List<Produto> produtos = produtoRepositoryPort.buscaProdutosPorCategoria(categoria);
        return produtos.stream().map(Produto::toProdutoDTO).collect(Collectors.toList());
    }

    @Override
    public List<String> getCategoriasDeProdutos() {
        return produtoRepositoryPort.buscaCategoriasDeProdutos();
    }

    @Override
    public ProdutoDTO criaProduto(ProdutoDTO produtoDTO) {
        Produto produtoCriado = produtoRepositoryPort.criaProduto(new Produto(produtoDTO));
        return produtoCriado.toProdutoDTO();
    }

    @Override
    public void removeProduto(UUID codigo) {
        produtoRepositoryPort.removeProduto(codigo);
    }

    @Override
    public void atualizaProduto(UUID codigo, ProdutoDTO produtoDTO) {
        produtoRepositoryPort.atualizaProduto(codigo, new Produto(produtoDTO));
    }
}
