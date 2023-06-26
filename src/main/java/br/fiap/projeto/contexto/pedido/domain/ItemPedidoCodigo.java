package br.fiap.projeto.contexto.pedido.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.UUID;

@Embeddable
public class ItemPedidoCodigo implements Serializable {
    @Column(name = "pedido_codigo")
    private UUID pedidoCodigo;

    @Column(name = "produto_codigo")
    private UUID produtoCodigo;

    public ItemPedidoCodigo(UUID pedidoCodigo, UUID produtoCodigo) {
        this.pedidoCodigo = pedidoCodigo;
        this.produtoCodigo = produtoCodigo;
    }

    public ItemPedidoCodigo() {

    }
}