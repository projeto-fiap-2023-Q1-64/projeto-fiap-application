package br.fiap.projeto.contexto.produto.domain.service;

import br.fiap.projeto.contexto.produto.domain.Produto;
import br.fiap.projeto.contexto.produto.domain.enums.CategoriaProduto;
import br.fiap.projeto.contexto.produto.domain.exception.EntradaInvalidaException;
import br.fiap.projeto.contexto.produto.domain.exception.ProdutoNaoEncontradoException;
import br.fiap.projeto.contexto.produto.domain.port.repository.ProdutoRepositoryPort;
import br.fiap.projeto.contexto.produto.domain.port.service.ProdutoServicePort;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class DomainProdutoService implements ProdutoServicePort {

    private final ProdutoRepositoryPort produtoRepositoryPort;

    public DomainProdutoService(ProdutoRepositoryPort produtoRepositoryPort) {
        this.produtoRepositoryPort = produtoRepositoryPort;
    }

    @Override
    public List<Produto> buscaTodos() {
        List<Produto> produtos = produtoRepositoryPort.buscaTodos();
        return produtos;
    }

    @Override
    public Produto buscaProduto(String codigo) throws ProdutoNaoEncontradoException {
        Optional<Produto> produto = produtoRepositoryPort.buscaProduto(codigo);
        produto.orElseThrow(() -> new ProdutoNaoEncontradoException());
        return produto.get();
    }

    @Override
    public List<Produto> buscaProdutosPorCategoria(CategoriaProduto categoria) {
        return produtoRepositoryPort.buscaProdutosPorCategoria(categoria);
    }

    @Override
    public List<String> getCategoriasDeProdutos() {
        return Arrays.stream(CategoriaProduto.values()).map(c -> c.name()).collect(Collectors.toList());
    }

    @Override
    public Produto criaProduto(Produto produto) throws EntradaInvalidaException {
        if (produto == null) {
            throw new EntradaInvalidaException("Entrada inválida! Produto não deve ser nulo!");
        }
        Produto newProduto = new Produto(UUID.randomUUID().toString(), produto.getNome(), produto.getDescricao(), produto.getPreco(), produto.getCategoria(), produto.getImagem(), produto.getTempoPreparoMin());
        return produtoRepositoryPort.criaProduto(newProduto);
    }

    @Override
    public void removeProduto(String codigo) throws ProdutoNaoEncontradoException {
        Optional<Produto> produtoRecuperado = produtoRepositoryPort.buscaProduto(codigo);
        produtoRecuperado.orElseThrow(() -> new ProdutoNaoEncontradoException());
        produtoRepositoryPort.removeProduto(codigo);
    }

    @Override
    public void atualizaProduto(String codigo, Produto produto) throws ProdutoNaoEncontradoException {
        Optional<Produto> produtoRecuperado = produtoRepositoryPort.buscaProduto(codigo);
        produtoRecuperado.orElseThrow(() -> new ProdutoNaoEncontradoException());
        produtoRepositoryPort.atualizaProduto(new Produto(codigo, produto.getNome(), produto.getDescricao(), produto.getPreco(), produto.getCategoria(), produto.getImagem(), produto.getTempoPreparoMin()));
    }
}
