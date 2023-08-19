package br.fiap.projeto.contexto.pedido.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class ItemPedidoCodigo implements Serializable {
    @Column(name = "pedido_codigo")
    private UUID pedidoCodigo;
    @Column(name = "produto_codigo")
    private UUID produtoCodigo;
}