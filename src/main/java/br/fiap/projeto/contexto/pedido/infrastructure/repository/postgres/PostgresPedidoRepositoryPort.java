package br.fiap.projeto.contexto.pedido.infrastructure.repository.postgres;

import br.fiap.projeto.contexto.pedido.domain.ItemPedido;
import br.fiap.projeto.contexto.pedido.domain.Pedido;
import br.fiap.projeto.contexto.pedido.domain.port.repository.PedidoRepositoryPort;
import br.fiap.projeto.contexto.pedido.infrastructure.entity.PedidoEntity;
import br.fiap.projeto.contexto.pedido.infrastructure.mapper.PedidoMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

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
    public Pedido salvar(Pedido pedido) {
        PedidoEntity novoPedido = springPedidoRepository.save(new PedidoEntity(PedidoMapper.toEntity(pedido)));
        return PedidoMapper.toDomain(novoPedido);
    }
    @Override
    public Optional<Pedido> buscaPedido(UUID codigo) {
        Optional<PedidoEntity> pedidoEntity = springPedidoRepository.findByCodigo(codigo);
        if (pedidoEntity.isPresent()) {
            return Optional.of(PedidoMapper.toDomain(pedidoEntity.get()));
        } else {
            return Optional.empty();
        }
    }
    @Override
    public List<Pedido> buscaTodos() {
        List<PedidoEntity> listaPedidoEntity = springPedidoRepository.findAll();
        return listaPedidoEntity.stream().map(PedidoMapper::toDomain).collect(Collectors.toList());
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
    public void aumentarQuantidade(UUID produto) {}
    @Override
    public void reduzirQuantidade(UUID produto) {}
    @Override
    public Integer calcularTempoTotalPreparo() {
        return null;
    }
    @Override
    public void adicionarProduto(UUID produto) {}
    @Override
    public void removerProduto(UUID produto) {}
    @Override
    public List<ItemPedido> listarItens() {
        return null;
    }
}