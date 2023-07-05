package br.fiap.projeto.contexto.comanda.domain.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CriarComandaDTO {
    private UUID codigoPedido;

}
