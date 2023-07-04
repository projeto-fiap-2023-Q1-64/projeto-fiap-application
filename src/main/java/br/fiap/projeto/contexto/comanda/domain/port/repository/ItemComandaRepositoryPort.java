package br.fiap.projeto.contexto.comanda.domain.port.repository;

import java.util.UUID;

import br.fiap.projeto.contexto.comanda.domain.ItemComanda;

public interface ItemComandaRepositoryPort {
    // List<ItemComanda> buscaItensComanda(Comanda comanda, UUID codigoProduto);

    ItemComanda criarItemComanda(UUID codigoComanda, ItemComanda itemComanda);
}
