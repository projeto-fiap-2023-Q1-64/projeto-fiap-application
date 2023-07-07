package br.fiap.projeto.contexto.pedido.infrastructure.integration.port;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CriaComanda {
    private UUID codigoPedido;
}
