package br.fiap.projeto.contexto.pedido.infrastructure.repository.postgres;

import br.fiap.projeto.contexto.pedido.domain.ItemPedido;
import br.fiap.projeto.contexto.pedido.domain.Pedido;
import br.fiap.projeto.contexto.pedido.domain.ProdutoPedido;
import br.fiap.projeto.contexto.pedido.domain.port.repository.PedidoRepositoryPort;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@Primary
public class PostgresPedidoRepositoryPort implements PedidoRepositoryPort {
    private final SpringPedidoRepository springPedidoRepository;
    public PostgresPedidoRepositoryPort(SpringPedidoRepository springPedidoRepository) {
        this.springPedidoRepository = springPedidoRepository;
    }
    @Override
    public Pedido criaPedido(Pedido pedido) {
        return null;
    }
    @Override
    public Pedido buscaPedido(UUID codigo) {
        return null;
    }
    @Override
    public List<Pedido> buscaTodos() {
        return null;
    }
    @Override
    public Pedido atualizaPedido(UUID codigo, Pedido pedido) {
        return null;
    }
    @Override
    public void removePedido(UUID codigo) {}
    @Override
    public Double calcularValorTotal() {
        return null;
    }
    @Override
    public void aumentarQuantidade(ProdutoPedido produto) {}
    @Override
    public void reduzirQuantidade(ProdutoPedido produto) {}
    @Override
    public Integer calcularTempoTotalPreparo() {
        return null;
    }
    @Override
    public void adicionarProduto(ProdutoPedido produto) {}
    @Override
    public void removerProduto(ProdutoPedido produto) {}
    @Override
    public List<ItemPedido> listarItens() {
        return null;
    }
}