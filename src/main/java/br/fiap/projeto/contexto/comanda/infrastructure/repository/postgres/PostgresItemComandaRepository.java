package br.fiap.projeto.contexto.comanda.infrastructure.repository.postgres;

import java.util.UUID;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import br.fiap.projeto.contexto.comanda.domain.Comanda;
import br.fiap.projeto.contexto.comanda.domain.ItemComanda;
import br.fiap.projeto.contexto.comanda.domain.port.repository.ItemComandaRepositoryPort;
import br.fiap.projeto.contexto.comanda.infrastructure.entity.ItemComandaEntity;

@Component
@Primary
public class PostgresItemComandaRepository implements ItemComandaRepositoryPort {

    private final SpringItemComandaRepository springItemComandaRepository;

    public PostgresItemComandaRepository(SpringItemComandaRepository springItemComandaRepository) {
        this.springItemComandaRepository = springItemComandaRepository;
    }

    // @Override
    // public List<ItemComanda> buscaItensComanda(UUID codigoPedido, UUID
    // codigoProduto) {
    // // List<ItemComandaEntity> resultados =
    // springItemComandaRepository.findByStatus(codigoPedido, codigoProduto);
    // // return
    // resultados.stream().map(ItemComandaEntity::toitemComanda).collect(Collectors.toList());
    // return null;
    // }

    @Override
    public ItemComanda criarItemComanda(UUID codigoComanda, ItemComanda itemComanda) {
        ItemComandaEntity itemComandaEntity = springItemComandaRepository.save(new ItemComandaEntity());
        return itemComandaEntity.toItemComanda();
    }

}
