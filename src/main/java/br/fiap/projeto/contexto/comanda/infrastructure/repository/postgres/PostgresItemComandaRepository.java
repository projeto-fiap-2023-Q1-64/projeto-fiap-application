package br.fiap.projeto.contexto.comanda.infrastructure.repository.postgres;



import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

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

    @Override
    public List<ItemComanda> buscaItensComanda(UUID codigoPedido, UUID codigoProduto) {
        List<ItemComandaEntity> resultados = springItemComandaRepository.findByStatus(codigoPedido, codigoProduto);
        return resultados.stream().map(ItemComandaEntity::toitemComanda).collect(Collectors.toList());
    }
    
    public ItemComanda criarItemComanda(UUID codigo, ItemComanda itemComanda) {
        ItemComandaEntity ice = springItemComandaRepository.save(new ItemComandaEntity(codigo,itemComanda));
        return ice.toitemComanda();
    }

}
