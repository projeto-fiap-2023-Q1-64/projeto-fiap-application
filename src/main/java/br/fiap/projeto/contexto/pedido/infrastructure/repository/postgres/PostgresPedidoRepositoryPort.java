package br.fiap.projeto.contexto.pedido.infrastructure.repository.postgres;

import br.fiap.projeto.contexto.pedido.domain.ItemPedido;
import br.fiap.projeto.contexto.pedido.domain.Pedido;
import br.fiap.projeto.contexto.pedido.domain.ProdutoPedido;
import br.fiap.projeto.contexto.pedido.domain.port.repository.PedidoRepositoryPort;
import br.fiap.projeto.contexto.pedido.infrastructure.entity.PedidoEntity;
import br.fiap.projeto.contexto.pedido.infrastructure.mapper.PedidoMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@Primary
public class PostgresPedidoRepositoryPort implements PedidoRepositoryPort {
    private final SpringPedidoRepository springPedidoRepository;
    public PostgresPedidoRepositoryPort(SpringPedidoRepository springPedidoRepository) {
        this.springPedidoRepository = springPedidoRepository;
    }
    @Override
    public Pedido criaPedido(Pedido pedido) {
        PedidoEntity novoPedido = springPedidoRepository.save(new PedidoEntity(PedidoMapper.toEntity(pedido)));
        return PedidoMapper.toDomain(novoPedido);
    }
    @Override
    public Pedido buscaPedido(UUID codigo) {
        Optional<PedidoEntity> pedidoEntity = springPedidoRepository.findByCodigo(codigo);
        pedidoEntity.orElseThrow(() -> new EntityNotFoundException("Pedido não encontrado!"));
        return PedidoMapper.toDomain(pedidoEntity.get());
    }
    @Override
    public List<Pedido> buscaTodos() {
        List<PedidoEntity> listaPedidoEntity = springPedidoRepository.findAll();
        return listaPedidoEntity.stream().map(PedidoMapper::toDomain).collect(Collectors.toList());
    }
    @Override
    public Pedido atualizaPedido(UUID codigo, Pedido pedido) {
        PedidoEntity pedidoEntity = new PedidoEntity(PedidoMapper.toEntity(this.buscaPedido(codigo)));
        pedidoEntity.atualizar(pedidoEntity);
        return PedidoMapper.toDomain(springPedidoRepository.save(pedidoEntity));
    }
    @Override
    public void removePedido(UUID codigo) {
        this.buscaPedido(codigo);
        springPedidoRepository.deleteByCodigo(codigo);
    }
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