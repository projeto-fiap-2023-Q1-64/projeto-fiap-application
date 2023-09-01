package br.fiap.projeto.contexto.pedido.adapter.controller.rest.response;

import br.fiap.projeto.contexto.pedido.entity.ItemPedido;
import br.fiap.projeto.contexto.pedido.entity.enums.StatusPedido;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
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
    private LocalDateTime dataCriacao;
}
