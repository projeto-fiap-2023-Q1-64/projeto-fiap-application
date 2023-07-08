package br.fiap.projeto.contexto.comanda.domain;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class ItemComandaChave {

    @Column(name = "codigo_produto")
    private UUID codigoProdutoComanda;

    @Column(name = "codigo_comanda")
    private UUID codigoComanda;

}
