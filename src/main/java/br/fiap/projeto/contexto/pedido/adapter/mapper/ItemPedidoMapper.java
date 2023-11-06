package br.fiap.projeto.contexto.pedido.adapter.mapper;

import br.fiap.projeto.contexto.pedido.entity.ItemPedido;
import br.fiap.projeto.contexto.pedido.external.repository.entity.ItemPedidoCodigo;
import br.fiap.projeto.contexto.pedido.external.repository.entity.ItemPedidoEntity;
import br.fiap.projeto.contexto.pedido.usecase.exception.InvalidStatusException;
import br.fiap.projeto.contexto.pedido.usecase.exception.NoItensException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ItemPedidoMapper {
    @Autowired
    private static PedidoMapper pedidoMapper;

    public static ItemPedidoEntity toEntity(ItemPedido itemPedido) {
        return new ItemPedidoEntity(new ItemPedidoCodigo(itemPedido.getPedidoCodigo(), itemPedido.getProdutoCodigo()),
                pedidoMapper.toEntityWithoutItens(itemPedido.getPedido()),
                itemPedido.getQuantidade(),
                itemPedido.getProdutoNome(),
                itemPedido.getProdutoDescricao(),
                itemPedido.getValorUnitario(),
                itemPedido.getCategoriaProduto(),
                itemPedido.getImagem(),
                itemPedido.getTempoPreparoMin());
    }

    public static ItemPedido toDomain(ItemPedidoEntity itemPedidoEntity)
            throws InvalidStatusException, NoItensException {
        return new ItemPedido(itemPedidoEntity.getCodigo().getPedidoCodigo(),
                itemPedidoEntity.getCodigo().getProdutoCodigo(),
                pedidoMapper.toDomainWithoutItens(itemPedidoEntity.getPedido()),
                itemPedidoEntity.getQuantidade(),
                itemPedidoEntity.getProdutoNome(),
                itemPedidoEntity.getProdutoDescricao(),
                itemPedidoEntity.getValorUnitario(),
                itemPedidoEntity.getCategoriaProduto(),
                itemPedidoEntity.getImagem(),
                itemPedidoEntity.getTempoPreparoMin());
    }
}
