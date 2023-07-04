package br.fiap.projeto.contexto.pedido.domain.dto;

import br.fiap.projeto.contexto.pedido.domain.ItemPedido;
import br.fiap.projeto.contexto.pedido.domain.enums.StatusPedido;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class PedidoDTO {
    private UUID codigo;
    private List<ItemPedido> itens;
    private UUID cliente;
    private StatusPedido status;
    private Double valorTotal;
}
