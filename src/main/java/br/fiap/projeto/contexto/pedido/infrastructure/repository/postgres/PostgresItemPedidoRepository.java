package br.fiap.projeto.contexto.pedido.infrastructure.repository.postgres;

import br.fiap.projeto.contexto.pedido.domain.ItemPedido;
import br.fiap.projeto.contexto.pedido.domain.ItemPedidoCodigo;
import br.fiap.projeto.contexto.pedido.domain.port.repository.ItemPedidoRepositoryPort;
import br.fiap.projeto.contexto.pedido.infrastructure.entity.ItemPedidoEntity;
import br.fiap.projeto.contexto.pedido.infrastructure.mapper.ItemPedidoMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Primary
public class PostgresItemPedidoRepository implements ItemPedidoRepositoryPort {
    private final SpringItemPedidoRepository springItemPedidoRepository;
    public PostgresItemPedidoRepository(SpringItemPedidoRepository springItemPedidoRepository) {
        this.springItemPedidoRepository = springItemPedidoRepository;
    }
    @Override
    public ItemPedido criaItemPedido(ItemPedido itemPedido) {
        ItemPedidoEntity novoItemPedido = springItemPedidoRepository.save(new ItemPedidoEntity(ItemPedidoMapper.toEntity(itemPedido)));
        return ItemPedidoMapper.toDomain(novoItemPedido);
    }
    @Override
    public ItemPedido buscaItemPedido(ItemPedidoCodigo codigo) {
        Optional<ItemPedidoEntity> itemPedidoEntity = springItemPedidoRepository.findByCodigo(codigo);
        itemPedidoEntity.orElseThrow(() -> new EntityNotFoundException("Item do Pedido não encontrado!"));
        return ItemPedidoMapper.toDomain(itemPedidoEntity.get());
    }
    @Override
    public List<ItemPedido> buscaTodos() {
        List<ItemPedidoEntity> listaItemPedidoEntity = springItemPedidoRepository.findAll();
        return listaItemPedidoEntity.stream().map(ItemPedidoMapper::toDomain).collect(Collectors.toList());
    }
    @Override
    public ItemPedido atualizaItemPedido(ItemPedido itemPedido) {
        ItemPedidoEntity itemPedidoEntity = new ItemPedidoEntity(ItemPedidoMapper.toEntity(this.buscaItemPedido(itemPedido.getCodigo())));
        itemPedidoEntity.atualizar(itemPedidoEntity);
        return ItemPedidoMapper.toDomain(springItemPedidoRepository.save(itemPedidoEntity));
    }
    @Override
    public void removeItemPedido(ItemPedidoCodigo codigo) {}
    @Override
    public Double calcularValorTotal() {return null;}
    @Override
    public Integer calcularTempoTotalPreparo() {return null;}
}