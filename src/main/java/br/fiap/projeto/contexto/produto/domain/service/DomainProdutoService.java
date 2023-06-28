package br.fiap.projeto.contexto.produto.domain.service;

import br.fiap.projeto.contexto.produto.application.rest.dto.ProdutoDTO;
import br.fiap.projeto.contexto.produto.domain.Produto;
import br.fiap.projeto.contexto.produto.domain.enums.CategoriaProduto;
import br.fiap.projeto.contexto.produto.domain.port.repository.ProdutoRepositoryPort;
import br.fiap.projeto.contexto.produto.domain.port.service.ProdutoServicePort;
import br.fiap.projeto.contexto.produto.domain.port.repository.ProdutoRepositoryPort;
import br.fiap.projeto.contexto.produto.domain.port.service.ProdutoServicePort;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class DomainProdutoService implements ProdutoServicePort {
public class DomainProdutoService implements ProdutoServicePort {

    private final ProdutoRepositoryPort produtoRepositoryPort;
    private final ProdutoRepositoryPort produtoRepositoryPort;

    public DomainProdutoService(ProdutoRepositoryPort produtoRepositoryPort) {
        this.produtoRepositoryPort = produtoRepositoryPort;
    public DomainProdutoService(ProdutoRepositoryPort produtoRepositoryPort) {
        this.produtoRepositoryPort = produtoRepositoryPort;
    }

    @Override
    public List<ProdutoDTO> buscaTodos() {
        List<Produto> produtos = produtoRepositoryPort.buscaTodos();
        return produtos.stream().map(Produto::toProdutoDTO).collect(Collectors.toList());
        List<Produto> produtos = produtoRepositoryPort.buscaTodos();
        return produtos.stream().map(Produto::toProdutoDTO).collect(Collectors.toList());
    }

    @Override
    public ProdutoDTO buscaProduto(String codigo) {
        Produto produto = produtoRepositoryPort.buscaProduto(codigo);
        return produto.toProdutoDTO();
    }

    @Override
    public List<ProdutoDTO> buscaProdutosPorCategoria(CategoriaProduto categoria) {
        List<Produto> produtos = produtoRepositoryPort.buscaProdutosPorCategoria(categoria);
        return produtos.stream().map(Produto::toProdutoDTO).collect(Collectors.toList());
    public List<ProdutoDTO> buscaProdutosPorCategoria(CategoriaProduto categoria) {
        List<Produto> produtos = produtoRepositoryPort.buscaProdutosPorCategoria(categoria);
        return produtos.stream().map(Produto::toProdutoDTO).collect(Collectors.toList());
    }

    @Override
    public List<String> getCategoriasDeProdutos() {
        return produtoRepositoryPort.buscaCategoriasDeProdutos();
        return produtoRepositoryPort.buscaCategoriasDeProdutos();
    }

    @Override
    public ProdutoDTO criaProduto(ProdutoDTO produtoDTO) {
        Produto produtoCriado = produtoRepositoryPort.criaProduto(new Produto(UUID.randomUUID().toString(), produtoDTO.getNome(), produtoDTO.getDescricao(), produtoDTO.getPreco(), CategoriaProduto.valueOf(produtoDTO.getCategoria()), produtoDTO.getImagem(), produtoDTO.getTempoPreparoMin()));
        return ProdutoDTO.getInstance(produtoCriado);
    }

    @Override
    public void removeProduto(String codigo) {
        produtoRepositoryPort.removeProduto(codigo);
    }

    @Override
    public void atualizaProduto(String codigo, ProdutoDTO produtoDTO) {
        produtoRepositoryPort.atualizaProduto(codigo, produtoDTO.toProduto());
    }
}
