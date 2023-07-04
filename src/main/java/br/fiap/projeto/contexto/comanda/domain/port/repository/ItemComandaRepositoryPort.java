package br.fiap.projeto.contexto.comanda.domain.port.repository;

import java.util.List;
import java.util.UUID;

import br.fiap.projeto.contexto.comanda.domain.ItemComanda;

public interface ItemComandaRepositoryPort {
    List<ItemComanda> buscaItensComanda(UUID codigoPedido, UUID codigoProduto);

    ItemComanda criarItemComanda(UUID codigo, ItemComanda itemComanda);
}
