package br.fiap.projeto.contexto.comanda.infrastructure.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.fiap.projeto.contexto.comanda.domain.Comanda;
import br.fiap.projeto.contexto.comanda.domain.enums.StatusComanda;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "comandas")

public class ComandaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID codigoComanda;

    @Column(nullable = false)
    private UUID codigoPedido;

    @Column(nullable = false)
    private StatusComanda status;

    @Column(nullable = false)
    private Date dataComanda;

    @OneToMany(mappedBy = "comanda", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<ItemComandaEntity> itens;

    public ComandaEntity(ComandaEntity comanda) {
        this.codigoComanda = comanda.getCodigoComanda();
        this.codigoPedido = comanda.getCodigoPedido();
        this.dataComanda = comanda.getDataComanda();
        this.itens = comanda.getItens();
        this.status = comanda.getStatus();
    }

    public void atualizar(ComandaEntity comanda) {
        this.codigoComanda = comanda.getCodigoComanda();
        this.codigoPedido = comanda.getCodigoPedido();
        this.dataComanda = comanda.getDataComanda();
        this.itens = comanda.getItens();
        this.status = comanda.getStatus();
    }

    // Trocar o null do itens ou tirar esse método
    public Comanda toComanda() {
        return new Comanda(codigoComanda, codigoPedido, null, status, dataComanda);
    }
}
