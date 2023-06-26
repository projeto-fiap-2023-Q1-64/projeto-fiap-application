package br.fiap.projeto.contexto.pedido.infrastructure.entity;

import br.fiap.projeto.contexto.pedido.domain.ItemPedido;
import br.fiap.projeto.contexto.pedido.domain.Pedido;
import br.fiap.projeto.contexto.pedido.domain.ProdutoPedido;
import br.fiap.projeto.contexto.pedido.domain.enums.StatusPedido;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
@Table(name="Pedidos")
public class PedidoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID codigo;
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<ItemPedidoEntity> itens;
    @Column(nullable = false)
    private UUID cliente;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusPedido status;
    @Column(nullable = false, precision = 2)
    private Double valorTotal;
    public PedidoEntity(UUID codigo,
                        List<ItemPedidoEntity> itens,
                        UUID cliente,
                        StatusPedido status,
                        Double valorTotal){
        this.codigo = codigo;
        this.itens = itens;
        this.cliente = cliente;
        this.status = status;
        this.valorTotal = valorTotal;
    }
    public PedidoEntity() {
    }

    public UUID getCodigo() {
        return codigo;
    }

    public List<ItemPedidoEntity> getItens() {
        return itens;
    }

    public UUID getCliente() {
        return cliente;
    }

    public StatusPedido getStatus() {
        return status;
    }

    public Double getValorTotal() {
        return valorTotal;
    }
}
