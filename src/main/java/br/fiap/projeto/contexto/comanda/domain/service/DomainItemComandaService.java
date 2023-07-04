package br.fiap.projeto.contexto.comanda.domain.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import br.fiap.projeto.contexto.comanda.domain.ItemComanda;
import br.fiap.projeto.contexto.comanda.domain.dto.ItemComandaDTO;
import br.fiap.projeto.contexto.comanda.domain.port.repository.ItemComandaRepositoryPort;
import br.fiap.projeto.contexto.comanda.domain.port.service.ItemComandaServicePort;

public class DomainItemComandaService implements ItemComandaServicePort {

    private final ItemComandaRepositoryPort itemComandaRepositoryPort;

    public DomainItemComandaService(ItemComandaRepositoryPort itemComandaRepositoryPort) {
        this.itemComandaRepositoryPort = itemComandaRepositoryPort;
    }

    @Override
    public List<ItemComandaDTO> buscaItensComanda(UUID codigo, UUID codigoProduto) {
        List<ItemComanda> comanda = itemComandaRepositoryPort.buscaItensComanda(codigo, codigoProduto);
        return comanda.stream().map(ItemComanda::toItemComandaDTO).collect(Collectors.toList());
    }

    @Override
    public ItemComandaDTO criarItemComanda(UUID codigoPedido, ItemComandaDTO itemComandaDTO) {
        return (itemComandaRepositoryPort.criarItemComanda(codigoPedido, new ItemComanda(itemComandaDTO))).toItemComandaDTO();
    }

   

}
