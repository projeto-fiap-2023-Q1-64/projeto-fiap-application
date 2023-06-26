package br.fiap.projeto.contexto.pedido.infrastructure.mapper;

import br.fiap.projeto.contexto.pedido.domain.ItemPedido;
import br.fiap.projeto.contexto.pedido.infrastructure.entity.ItemPedidoEntity;
import br.fiap.projeto.contexto.pedido.infrastructure.entity.ProdutoPedidoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ItemPedidoMapper {
    @Autowired
    private static PedidoMapper pedidoMapper;
    public static ItemPedidoEntity toEntity(ItemPedido itemPedido) {
        return new ItemPedidoEntity(itemPedido.getCodigo(),
                pedidoMapper.toEntityWithoutItens(itemPedido.getPedido()),
                new ProdutoPedidoEntity(itemPedido.getProduto()),
                itemPedido.getQuantidade(),
                itemPedido.getValorUnitario());
    }
    public static ItemPedido toDomain(ItemPedidoEntity itemPedidoEntity) {
        return new ItemPedido(itemPedidoEntity.getCodigo(),
                pedidoMapper.toDomainWithoutItens(itemPedidoEntity.getPedido()),
                itemPedidoEntity.getProduto().toDomain(),
                itemPedidoEntity.getQuantidade(),
                itemPedidoEntity.getValorUnitario());
    }
}
