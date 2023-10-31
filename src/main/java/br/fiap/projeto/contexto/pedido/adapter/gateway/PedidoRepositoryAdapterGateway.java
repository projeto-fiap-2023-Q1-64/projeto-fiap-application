package br.fiap.projeto.contexto.pedido.adapter.gateway;

import br.fiap.projeto.contexto.pedido.adapter.mapper.PedidoMapper;
import br.fiap.projeto.contexto.pedido.entity.Pedido;
import br.fiap.projeto.contexto.pedido.entity.enums.StatusPedido;
import br.fiap.projeto.contexto.pedido.external.repository.entity.PedidoEntity;
import br.fiap.projeto.contexto.pedido.external.repository.postgres.SpringPedidoRepository;
import br.fiap.projeto.contexto.pedido.usecase.exception.InvalidStatusException;
import br.fiap.projeto.contexto.pedido.usecase.exception.NoItensException;
import br.fiap.projeto.contexto.pedido.usecase.port.adaptergateway.IPedidoRepositoryAdapterGateway;
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
    public Pedido salvar(Pedido pedido) throws InvalidStatusException, NoItensException {
        PedidoEntity pedidoEntity = springPedidoRepository.save(new PedidoEntity(PedidoMapper.toEntity(pedido)));
        return PedidoMapper.toDomain(pedidoEntity);
    }

    @Override
    public Optional<Pedido> buscaPedido(UUID codigo) {
        Optional<PedidoEntity> pedidoEntity = springPedidoRepository.findByCodigo(codigo);
        return pedidoEntity.map(t -> {
            try {
                return PedidoMapper.toDomain(t);
            } catch (InvalidStatusException e) {
                e.printStackTrace();
            } catch (NoItensException e) {
                e.printStackTrace();
            }
            return null;
        });
    }

    @Override
    public List<Pedido> buscaTodos() {
        List<PedidoEntity> listaPedidoEntity = springPedidoRepository.findAll();
        return listaPedidoEntity.stream().map(t -> {
            try {
                return PedidoMapper.toDomain(t);
            } catch (InvalidStatusException e) {
                e.printStackTrace();
            } catch (NoItensException e) {
                e.printStackTrace();
            }
            return null;
        }).collect(Collectors.toList());
    }

    @Override
    public List<Pedido> buscaPedidosPorStatus(StatusPedido statusPedido) {
        List<PedidoEntity> listaPedidoEntity = springPedidoRepository.findByStatusEquals(statusPedido);
        return listaPedidoEntity.stream().map(t -> {
            try {
                return PedidoMapper.toDomain(t);
            } catch (InvalidStatusException e) {
                e.printStackTrace();
            } catch (NoItensException e) {
                e.printStackTrace();
            }
            return null;
        }).collect(Collectors.toList());
    }

    @Override
    public List<Pedido> buscaPedidorPorStatuses(List<StatusPedido> statuses) {
        List<PedidoEntity> listaPedidoEntity = springPedidoRepository.findByStatusIn(statuses);
        return listaPedidoEntity.stream().map(t -> {
            try {
                return PedidoMapper.toDomain(t);
            } catch (InvalidStatusException e) {
                e.printStackTrace();
            } catch (NoItensException e) {
                e.printStackTrace();
            }
            return null;
        }).collect(Collectors.toList());
    }
}