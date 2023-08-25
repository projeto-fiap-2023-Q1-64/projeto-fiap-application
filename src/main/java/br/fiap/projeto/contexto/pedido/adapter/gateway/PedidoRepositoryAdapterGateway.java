package br.fiap.projeto.contexto.pedido.adapter.gateway;

import br.fiap.projeto.contexto.pedido.entity.Pedido;
import br.fiap.projeto.contexto.pedido.entity.enums.StatusPedido;
import br.fiap.projeto.contexto.pedido.external.repository.postgres.SpringPedidoRepository;
import br.fiap.projeto.contexto.pedido.usecase.port.IPedidoRepositoryAdapterGateway;
import br.fiap.projeto.contexto.pedido.external.repository.entity.PedidoEntity;
import br.fiap.projeto.contexto.pedido.adapter.mapper.PedidoMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@Primary
public class PedidoRepositoryAdapterGateway implements IPedidoRepositoryAdapterGateway {
    private final SpringPedidoRepository springPedidoRepository;
    public PedidoRepositoryAdapterGateway(SpringPedidoRepository springPedidoRepository) {
        this.springPedidoRepository = springPedidoRepository;
    }
    @Override
    @Transactional
    public Pedido salvar(Pedido pedido) {
        PedidoEntity pedidoEntity = springPedidoRepository.save(new PedidoEntity(PedidoMapper.toEntity(pedido)));
        return PedidoMapper.toDomain(pedidoEntity);
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
    public List<Pedido> buscaPedidosPorStatus(StatusPedido statusPedido) {
        List<PedidoEntity> listaPedidoEntity = springPedidoRepository.findByStatusEquals(statusPedido);
        return listaPedidoEntity.stream().map(PedidoMapper::toDomain).collect(Collectors.toList());
    }
}