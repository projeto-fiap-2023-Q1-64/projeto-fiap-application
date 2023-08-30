package br.fiap.projeto.contexto.comanda.adapter.controller.rest.dto;

import br.fiap.projeto.contexto.comanda.entity.enums.StatusComanda;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BuscaPorStatusComandaDTO {
    private StatusComanda statusComanda;

}
