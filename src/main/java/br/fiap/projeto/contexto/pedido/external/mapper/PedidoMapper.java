package br.fiap.projeto.contexto.pedido.external.mapper;

import br.fiap.projeto.contexto.pedido.entity.ItemPedido;
import br.fiap.projeto.contexto.pedido.entity.Pedido;
import br.fiap.projeto.contexto.pedido.external.repository.entity.ItemPedidoEntity;
import br.fiap.projeto.contexto.pedido.external.repository.entity.PedidoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PedidoMapper {
        @Autowired
        private ItemPedidoMapper itemPedidoMapper;

        public static PedidoEntity toEntity(Pedido pedido) {
                List<ItemPedidoEntity> itensPedidoEntity = pedido.getItens().stream()
                                .map(ItemPedidoMapper::toEntity)
                                .collect(Collectors.toList());
                return new PedidoEntity(pedido.getCodigo(),
                                itensPedidoEntity,
                                pedido.getCliente(),
                                pedido.getStatus(),
                                pedido.getValorTotal());
        }

        public static PedidoEntity toEntityWithoutItens(Pedido pedido) {
                return new PedidoEntity(pedido.getCodigo(),
                                null,
                                pedido.getCliente(),
                                pedido.getStatus(),
                                pedido.getValorTotal());
        }

        public static Pedido toDomain(PedidoEntity pedidoEntity) {
                List<ItemPedido> itensPedido = pedidoEntity.getItens().stream()
                                .map(ItemPedidoMapper::toDomain)
                                .collect(Collectors.toList());
                return new Pedido(pedidoEntity.getCodigo(),
                                itensPedido,
                                pedidoEntity.getCliente(),
                                pedidoEntity.getStatus(),
                                pedidoEntity.getValorTotal());
        }

        public static Pedido toDomainWithoutItens(PedidoEntity pedidoEntity) {
                return new Pedido(pedidoEntity.getCodigo(),
                                null,
                                pedidoEntity.getCliente(),
                                pedidoEntity.getStatus(),
                                pedidoEntity.getValorTotal());
        }
}
