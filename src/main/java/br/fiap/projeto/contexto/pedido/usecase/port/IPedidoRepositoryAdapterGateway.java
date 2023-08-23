package br.fiap.projeto.contexto.pedido.usecase.port;

import br.fiap.projeto.contexto.pedido.entity.ItemPedido;
import br.fiap.projeto.contexto.pedido.entity.Pedido;
import br.fiap.projeto.contexto.pedido.entity.enums.StatusPedido;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IPedidoRepositoryAdapterGateway {

    Pedido salvar(Pedido pedido);
    Optional<Pedido> buscaPedido(UUID codigo);
    List<Pedido> buscaTodos();
    List<Pedido> buscaPedidosPorStatus(StatusPedido statusPedido);
}