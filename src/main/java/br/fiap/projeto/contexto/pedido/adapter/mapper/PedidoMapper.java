package br.fiap.projeto.contexto.pedido.adapter.mapper;

import br.fiap.projeto.contexto.pedido.entity.ItemPedido;
import br.fiap.projeto.contexto.pedido.entity.Pedido;
import br.fiap.projeto.contexto.pedido.external.repository.entity.ItemPedidoEntity;
import br.fiap.projeto.contexto.pedido.external.repository.entity.PedidoEntity;
import br.fiap.projeto.contexto.pedido.usecase.exception.InvalidStatusException;
import br.fiap.projeto.contexto.pedido.usecase.exception.NoItensException;

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
                                pedido.getValorTotal(),
                                pedido.getDataCriacao());
        }

        public static PedidoEntity toEntityWithoutItens(Pedido pedido) {
                return new PedidoEntity(pedido.getCodigo(),
                                null,
                                pedido.getCliente(),
                                pedido.getStatus(),
                                pedido.getValorTotal(),
                                pedido.getDataCriacao());
        }

        public static Pedido toDomain(PedidoEntity pedidoEntity) throws InvalidStatusException, NoItensException {
                List<ItemPedido> itensPedido = pedidoEntity.getItens().stream()
                                .map(t -> {
                                        try {
                                                return ItemPedidoMapper.toDomain(t);
                                        } catch (InvalidStatusException e) {
                                                e.printStackTrace();
                                        } catch (NoItensException e) {
                                                e.printStackTrace();
                                        }
                                        return null;
                                })
                                .collect(Collectors.toList());
                return new Pedido(pedidoEntity.getCodigo(),
                                itensPedido,
                                pedidoEntity.getCliente(),
                                pedidoEntity.getStatus(),
                                pedidoEntity.getValorTotal(),
                                pedidoEntity.getDataCriacao());
        }

        public static Pedido toDomainWithoutItens(PedidoEntity pedidoEntity)
                        throws InvalidStatusException, NoItensException {
                return new Pedido(pedidoEntity.getCodigo(),
                                null,
                                pedidoEntity.getCliente(),
                                pedidoEntity.getStatus(),
                                pedidoEntity.getValorTotal(),
                                pedidoEntity.getDataCriacao());
        }
}
