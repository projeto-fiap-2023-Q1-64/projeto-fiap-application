package br.fiap.projeto.contexto.pedido.application.rest.response;

import br.fiap.projeto.contexto.pedido.domain.ItemPedido;
import br.fiap.projeto.contexto.pedido.domain.enums.StatusPedido;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PedidoDTO {
    private UUID codigo;
    private List<ItemPedido> itens;
    private UUID cliente;
    private StatusPedido status;
    private Double valorTotal;
}
