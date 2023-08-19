package br.fiap.projeto.contexto.pedido.adapter.controller.rest.request;

import br.fiap.projeto.contexto.pedido.external.integration.port.Cliente;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PedidoCriarDTO {
    private UUID cliente;
    public PedidoCriarDTO(Cliente cliente){
        this.cliente = UUID.fromString(cliente.getCodigo());
    }
}
