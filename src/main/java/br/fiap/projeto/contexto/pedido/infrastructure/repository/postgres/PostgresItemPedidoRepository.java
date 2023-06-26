package br.fiap.projeto.contexto.pedido.infrastructure.repository.postgres;

import br.fiap.projeto.contexto.pedido.domain.ItemPedido;
import br.fiap.projeto.contexto.pedido.domain.port.repository.ItemPedidoRepositoryPort;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@Primary
public class PostgresItemPedidoRepository implements ItemPedidoRepositoryPort {
    private final SpringItemPedidoRepository springItemPedidoRepository;
    public PostgresItemPedidoRepository(SpringItemPedidoRepository springItemPedidoRepository) {
        this.springItemPedidoRepository = springItemPedidoRepository;
    }
    @Override
    public ItemPedido criaItemPedido(ItemPedido itemPedido) {return null;}
    @Override
    public ItemPedido buscaItemPedido(UUID codigo) {return null;}
    @Override
    public List<ItemPedido> buscaTodos() {return null;}
    @Override
    public ItemPedido atualizaItemPedido(ItemPedido itemPedido) {return null;}
    @Override
    public void removeItemPedido(UUID codigo) {}
    @Override
    public Double calcularValorTotal() {return null;}
    @Override
    public Integer calcularTempoTotalPreparo() {return null;}
}