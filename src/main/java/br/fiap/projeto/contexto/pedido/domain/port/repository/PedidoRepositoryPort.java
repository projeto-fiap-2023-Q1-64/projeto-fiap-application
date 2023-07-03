package br.fiap.projeto.contexto.pedido.domain.port.repository;

import br.fiap.projeto.contexto.pedido.domain.ItemPedido;
import br.fiap.projeto.contexto.pedido.domain.Pedido;
import br.fiap.projeto.contexto.pedido.domain.dto.PedidoDTO;
import br.fiap.projeto.contexto.pedido.domain.enums.StatusPedido;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PedidoRepositoryPort {

    Pedido salvar(Pedido pedido);
    Optional<Pedido> buscaPedido(UUID codigo);
    List<Pedido> buscaTodos();
    void removePedido(UUID codigo);
    Double calcularValorTotal();
    void aumentarQuantidade(UUID produto);
    void reduzirQuantidade(UUID produto);
    Integer calcularTempoTotalPreparo();
    void adicionarProduto(UUID produto);
    void removerProduto(UUID produto);
    List<ItemPedido> listarItens();

    List<Pedido> buscaPedidosPorStatus(StatusPedido statusPedido);
}