package br.fiap.projeto.contexto.comanda.domain.port.repository;

import java.util.UUID;

import br.fiap.projeto.contexto.comanda.domain.ProdutoComanda;

public interface ProdutoComandaRepositoryPort {
    ProdutoComanda criaProdutoComanda(ProdutoComanda produto);

    ProdutoComanda buscaProdutoComanda(UUID codigo);
}
