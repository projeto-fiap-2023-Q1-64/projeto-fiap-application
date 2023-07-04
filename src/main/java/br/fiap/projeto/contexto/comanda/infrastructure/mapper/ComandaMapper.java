package br.fiap.projeto.contexto.comanda.infrastructure.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.fiap.projeto.contexto.comanda.domain.Comanda;
import br.fiap.projeto.contexto.comanda.domain.ItemComanda;
import br.fiap.projeto.contexto.comanda.infrastructure.entity.ComandaEntity;
import br.fiap.projeto.contexto.comanda.infrastructure.entity.ItemComandaEntity;

@Component
public class ComandaMapper {

        @Autowired
        private ComandaMapper ComandaMapper;

        public static ComandaEntity toEntity(Comanda comanda) {
                List<ItemComandaEntity> itensComandaEntity = comanda.getItens().stream()
                                .map(ItemComandaMapper::toEntity)
                                .collect(Collectors.toList());
                return new ComandaEntity(comanda.getCodigoComanda(),
                                comanda.getCodigoPedido(),
                                comanda.getStatus(),
                                comanda.getDataComanda(),
                                itensComandaEntity);
        }

        public static ComandaEntity toEntityWithoutItens(Comanda comanda) {
                return new ComandaEntity(comanda.getCodigoComanda(),
                                comanda.getCodigoPedido(),
                                comanda.getStatus(),
                                comanda.getDataComanda(),
                                null);
        }

        public static Comanda toDomain(ComandaEntity comandaEntity) {
                List<ItemComanda> itensComanda = comandaEntity.getItens().stream()
                                .map(ItemComandaMapper::toDomain)
                                .collect(Collectors.toList());
                return new Comanda(comandaEntity.getCodigoComanda(),
                                comandaEntity.getCodigoPedido(),
                                itensComanda,
                                comandaEntity.getStatus(),
                                comandaEntity.getDataComanda());
        }

        public static Comanda toDomainWithoutItens(ComandaEntity comandaEntity) {
                return new Comanda(comandaEntity.getCodigoComanda(),
                                comandaEntity.getCodigoPedido(),
                                null,
                                comandaEntity.getStatus(),
                                comandaEntity.getDataComanda());
        }
}
