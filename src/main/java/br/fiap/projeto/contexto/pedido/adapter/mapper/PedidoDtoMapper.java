package br.fiap.projeto.contexto.pedido.adapter.mapper;

import br.fiap.projeto.contexto.pedido.adapter.controller.rest.response.PedidoDTO;
import br.fiap.projeto.contexto.pedido.entity.Pedido;
import org.springframework.stereotype.Component;

@Component
public class PedidoDtoMapper {
    public static PedidoDTO toDto(Pedido pedido){
        return new PedidoDTO(pedido.getCodigo(),
                pedido.getItens(),
                pedido.getCliente(),
                pedido.getStatus(),
                pedido.getValorTotal(),
                pedido.getDataCriacao());
    }
}
