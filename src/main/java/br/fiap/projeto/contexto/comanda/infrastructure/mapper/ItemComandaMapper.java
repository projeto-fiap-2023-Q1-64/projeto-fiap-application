package br.fiap.projeto.contexto.comanda.infrastructure.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.fiap.projeto.contexto.comanda.domain.ItemComanda;
import br.fiap.projeto.contexto.comanda.infrastructure.entity.ItemComandaEntity;

@Component
public class ItemComandaMapper {
    @Autowired
    private static ComandaMapper comandaMapper;

    public static ItemComandaEntity toEntity(ItemComanda itemComanda) {
        return new ItemComandaEntity(itemComanda.getChave(),
                comandaMapper.toEntityWithoutItens(itemComanda.getComanda()),
                itemComanda.getProduto(),
                itemComanda.getQtde());
    }

    // public static ItemComanda toDomain(ItemComandaEntity itemComandaEntity) {
    // return new ItemComanda(itemComandaEntity.getChave(),
    // comandaMapper.toDomain((itemComandaEntity.getComanda())),
    // itemComandaEntity.getProduto(),
    // itemComandaEntity.getQtde());

    // }
}