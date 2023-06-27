package br.fiap.projeto.contexto.pedido.infrastructure.repository.postgres;

import br.fiap.projeto.contexto.pedido.domain.ItemPedidoCodigo;
import br.fiap.projeto.contexto.pedido.infrastructure.entity.ItemPedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpringItemPedidoRepository extends JpaRepository<ItemPedidoEntity, ItemPedidoCodigo> {
    Optional<ItemPedidoEntity> findByCodigo(ItemPedidoCodigo codigo);
    void deleteByCodigo(ItemPedidoCodigo codigo);
}