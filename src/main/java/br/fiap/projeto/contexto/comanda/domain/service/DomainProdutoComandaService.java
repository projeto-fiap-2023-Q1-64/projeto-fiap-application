package br.fiap.projeto.contexto.comanda.domain.service;

import java.util.UUID;

import br.fiap.projeto.contexto.comanda.domain.ProdutoComanda;
import br.fiap.projeto.contexto.comanda.domain.dto.ProdutoComandaDTO;
import br.fiap.projeto.contexto.comanda.domain.port.repository.ProdutoComandaRepositoryPort;
import br.fiap.projeto.contexto.comanda.domain.port.service.ProdutoComandaServicePort;

public class DomainProdutoComandaService implements ProdutoComandaServicePort {

    private final ProdutoComandaRepositoryPort produtoComandaRepositoryPort;

    public DomainProdutoComandaService(ProdutoComandaRepositoryPort produtoComandaRepositoryPort) {
        this.produtoComandaRepositoryPort = produtoComandaRepositoryPort;
    }

    @Override
    public ProdutoComandaDTO criaProdutoComanda(ProdutoComandaDTO produto) {
        return (produtoComandaRepositoryPort.criaProdutoComanda(new ProdutoComanda(produto))).toProdutoComandaDTO();
    }

    @Override
    public ProdutoComandaDTO buscaProdutoComanda(UUID codigo) {
        ProdutoComanda produto = produtoComandaRepositoryPort.buscaProdutoComanda(codigo);
        return produto.toProdutoComandaDTO();
    }
}