package br.fiap.projeto.contexto.comanda.infrastructure.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.fiap.projeto.contexto.comanda.domain.Comanda;
import br.fiap.projeto.contexto.comanda.domain.enums.StatusComanda;

@Entity
@Table(name = "comandas")

public class ComandaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID codigoPedido;

    @Column(nullable = false)
    private StatusComanda status;

    @Column(nullable = false)
    private Date dataComanda;

    @OneToMany(mappedBy = "comanda")
    private List<ItemComandaEntity> itens = new ArrayList<ItemComandaEntity>();

    public ComandaEntity() {
    }

    public ComandaEntity(Comanda comanda) {
        this.codigoPedido = comanda.getCodigoPedido();
        this.dataComanda = comanda.getDataComanda();
        // this.itens = comanda.getItens();
        this.status = comanda.getStatus();
    }

    public void atualizar(Comanda comanda) {
        this.codigoPedido = comanda.getCodigoPedido();
        this.dataComanda = comanda.getDataComanda();
        // this.itens = comanda.getItens();
        this.status = comanda.getStatus();
    }

    // Trocar o null do itens
    public Comanda toComanda() {
        return new Comanda(codigoPedido, null, status, dataComanda);
    }
}
