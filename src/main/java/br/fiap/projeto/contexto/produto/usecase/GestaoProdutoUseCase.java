package br.fiap.projeto.contexto.produto.usecase;

import br.fiap.projeto.contexto.produto.entity.Produto;
import br.fiap.projeto.contexto.produto.entity.enums.CategoriaProduto;
import br.fiap.projeto.contexto.produto.usecase.exception.EntradaInvalidaException;
import br.fiap.projeto.contexto.produto.usecase.exception.ProdutoNaoEncontradoException;
import br.fiap.projeto.contexto.produto.usecase.port.IProdutoRepositoryAdapterGateway;
import br.fiap.projeto.contexto.produto.usecase.port.IGestaoProdutoUseCase;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class GestaoProdutoUseCase implements IGestaoProdutoUseCase {

    private final IProdutoRepositoryAdapterGateway produtoAdapterGateway;

    public GestaoProdutoUseCase(IProdutoRepositoryAdapterGateway produtoAdapterGateway) {
        this.produtoAdapterGateway = produtoAdapterGateway;
    }

    @Override
    public List<Produto> buscaTodos() {
        List<Produto> produtos = produtoAdapterGateway.buscaTodos();
        return produtos;
    }

    @Override
    public Produto buscaProduto(String codigo) throws ProdutoNaoEncontradoException {
        Optional<Produto> produto = produtoAdapterGateway.buscaProduto(codigo);
        produto.orElseThrow(() -> new ProdutoNaoEncontradoException());
        return produto.get();
    }

    @Override
    public List<Produto> buscaProdutosPorCategoria(CategoriaProduto categoria) {
        return produtoAdapterGateway.buscaProdutosPorCategoria(categoria);
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
        return produtoAdapterGateway.criaProduto(newProduto);
    }

    @Override
    public Void removeProduto(String codigo) throws ProdutoNaoEncontradoException {
        Optional<Produto> produtoRecuperado = produtoAdapterGateway.buscaProduto(codigo);
        produtoRecuperado.orElseThrow(() -> new ProdutoNaoEncontradoException());
        produtoAdapterGateway.removeProduto(codigo);
        return null;
    }

    @Override
    public void atualizaProduto(String codigo, Produto produto) throws ProdutoNaoEncontradoException {
        Optional<Produto> produtoRecuperado = produtoAdapterGateway.buscaProduto(codigo);
        produtoRecuperado.orElseThrow(() -> new ProdutoNaoEncontradoException());
        produtoAdapterGateway.atualizaProduto(new Produto(codigo, produto.getNome(), produto.getDescricao(), produto.getPreco(), produto.getCategoria(), produto.getImagem(), produto.getTempoPreparoMin()));
    }
}
