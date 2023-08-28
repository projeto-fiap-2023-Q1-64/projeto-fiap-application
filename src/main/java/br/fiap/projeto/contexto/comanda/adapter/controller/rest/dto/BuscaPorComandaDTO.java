package br.fiap.projeto.contexto.comanda.adapter.controller.rest.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BuscaPorComandaDTO {
    private UUID codigoComanda;
}
