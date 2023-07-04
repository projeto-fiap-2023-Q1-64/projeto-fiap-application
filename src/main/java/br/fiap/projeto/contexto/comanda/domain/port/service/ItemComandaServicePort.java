package br.fiap.projeto.contexto.comanda.domain.port.service;

import java.util.List;
import java.util.UUID;

import br.fiap.projeto.contexto.comanda.domain.dto.ItemComandaDTO;

public interface ItemComandaServicePort {
    List<ItemComandaDTO> buscaItensComanda(UUID codigoPedido, UUID codigoProduto);

    ItemComandaDTO criarItemComanda(UUID codigoPedido, ItemComandaDTO itemComandaDTO);
}
