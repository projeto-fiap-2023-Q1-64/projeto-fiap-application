package br.fiap.projeto.contexto.produto.adapter.controller.port;

import br.fiap.projeto.contexto.produto.entity.enums.CategoriaProduto;
import br.fiap.projeto.contexto.produto.adapter.controller.rest.request.ProdutoDTORequest;
import br.fiap.projeto.contexto.produto.adapter.controller.rest.response.ProdutoDTOResponse;
import br.fiap.projeto.contexto.produto.usecase.exception.EntradaInvalidaException;
import br.fiap.projeto.contexto.produto.usecase.exception.ProdutoNaoEncontradoException;

import java.util.List;

public interface IProdutoRestAdapterController {
    public List<ProdutoDTOResponse> buscaTodos();
    public ProdutoDTOResponse buscaProduto(String codigo) throws ProdutoNaoEncontradoException;
    public List<ProdutoDTOResponse> buscaProdutosPorCategoria(CategoriaProduto categoria);
    public ProdutoDTOResponse criaProduto(ProdutoDTORequest produtoDTORequest) throws EntradaInvalidaException;
    public void removeProduto(String codigo) throws ProdutoNaoEncontradoException;
    public void atualizaProduto(String codigo, ProdutoDTORequest produtoDTO) throws ProdutoNaoEncontradoException;
    public List<String> getCategoriasDeProdutos();
}
