package br.fiap.projeto.contexto.pedido.infrastructure.entity;

import br.fiap.projeto.contexto.pedido.domain.enums.StatusPedido;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="Pedidos")
public class PedidoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID codigo;
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
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
    public PedidoEntity(PedidoEntity pedido){
        this.codigo = pedido.getCodigo();
        this.itens = pedido.getItens();
        this.cliente = pedido.getCliente();
        this.status = pedido.getStatus();
        this.valorTotal = pedido.getValorTotal();
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
    public PedidoEntity() {
    }
    public void atualizar(PedidoEntity pedidoEntity) {
        this.codigo = pedidoEntity.getCodigo();
        this.cliente = pedidoEntity.getCliente();
        this.itens = pedidoEntity.getItens();
        this.status = pedidoEntity.getStatus();
        this.valorTotal = pedidoEntity.getValorTotal();
    }
}