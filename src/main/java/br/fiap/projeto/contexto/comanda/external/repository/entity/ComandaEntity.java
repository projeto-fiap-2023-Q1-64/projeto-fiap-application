package br.fiap.projeto.contexto.comanda.external.repository.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.fiap.projeto.contexto.comanda.entity.Comanda;
import br.fiap.projeto.contexto.comanda.entity.enums.StatusComanda;
import br.fiap.projeto.contexto.comanda.external.exception.ExceptionMessage;
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

    @Column(nullable = false, unique = true)
    private UUID codigoPedido;

    @Column(nullable = false)
    private StatusComanda status;

    public ComandaEntity(Comanda comanda) {
        this.codigoComanda = comanda.getCodigoComanda();
        this.codigoPedido = comanda.getCodigoPedido();
        this.status = comanda.getStatus();
    }

    public void atualizar(ComandaEntity comanda) {
        this.codigoComanda = comanda.getCodigoComanda();
        this.codigoPedido = comanda.getCodigoPedido();
        this.status = comanda.getStatus();
    }

    public Comanda toComanda() throws ExceptionMessage {
        return new Comanda(codigoComanda, codigoPedido, status);
    }
}
