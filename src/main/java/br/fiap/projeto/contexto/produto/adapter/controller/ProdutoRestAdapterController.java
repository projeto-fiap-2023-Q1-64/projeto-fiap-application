package br.fiap.projeto.contexto.produto.adapter.controller;

import br.fiap.projeto.contexto.produto.adapter.controller.rest.request.ProdutoDTORequest;
import br.fiap.projeto.contexto.produto.adapter.controller.rest.response.ProdutoDTOResponse;
import br.fiap.projeto.contexto.produto.adapter.controller.port.IProdutoRestAdapterController;
import br.fiap.projeto.contexto.produto.entity.Produto;
import br.fiap.projeto.contexto.produto.entity.enums.CategoriaProduto;
import br.fiap.projeto.contexto.produto.usecase.exception.EntradaInvalidaException;
import br.fiap.projeto.contexto.produto.usecase.exception.ProdutoNaoEncontradoException;
import br.fiap.projeto.contexto.produto.usecase.port.IGestaoProdutoUseCase;

import java.util.List;
import java.util.stream.Collectors;

public class ProdutoRestAdapterController implements IProdutoRestAdapterController {

    private final IGestaoProdutoUseCase produtoUseCase;

    public ProdutoRestAdapterController(IGestaoProdutoUseCase produtoUseCase) {
        this.produtoUseCase = produtoUseCase;
    }

    @Override
    public List<ProdutoDTOResponse> buscaTodos() {
        List<Produto> produtos = this.produtoUseCase.buscaTodos();
        return produtos.stream().map(ProdutoDTOResponse::newInstanceByProduto).collect(Collectors.toList());
    }

    @Override
    public ProdutoDTOResponse buscaProduto(String codigo) throws ProdutoNaoEncontradoException {
        Produto produto = this.produtoUseCase.buscaProduto(codigo);
        return ProdutoDTOResponse.newInstanceByProduto(produto);
    }

    @Override
    public List<ProdutoDTOResponse> buscaProdutosPorCategoria(CategoriaProduto categoria) {
        List<Produto> produtos = this.produtoUseCase.buscaProdutosPorCategoria(categoria);
        return produtos.stream().map(ProdutoDTOResponse::newInstanceByProduto).collect(Collectors.toList());
    }

    @Override
    public List<String> getCategoriasDeProdutos() {
        return this.produtoUseCase.getCategoriasDeProdutos();
    }

    @Override
    public ProdutoDTOResponse criaProduto(ProdutoDTORequest produtoDTORequest) throws EntradaInvalidaException {
        Produto produto = this.produtoUseCase.criaProduto(produtoDTORequest.toProduto());
        return ProdutoDTOResponse.newInstanceByProduto(produto);
    }

    @Override
    public void removeProduto(String codigo) throws ProdutoNaoEncontradoException {
        this.produtoUseCase.removeProduto(codigo);
    }

    @Override
    public void atualizaProduto(String codigo, ProdutoDTORequest produtoDTO) throws ProdutoNaoEncontradoException {
        this.produtoUseCase.atualizaProduto(codigo, produtoDTO.toProduto());
    }
}
