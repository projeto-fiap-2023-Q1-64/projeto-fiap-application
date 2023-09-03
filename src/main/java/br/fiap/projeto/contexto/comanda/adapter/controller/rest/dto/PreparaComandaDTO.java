package br.fiap.projeto.contexto.comanda.adapter.controller.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PreparaComandaDTO {
    private UUID codigoPedido;
}
