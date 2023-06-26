package br.fiap.projeto.contexto.pedido.infrastructure.repository.postgres;

import br.fiap.projeto.contexto.pedido.infrastructure.entity.ItemPedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SpringItemPedidoRepository extends JpaRepository<ItemPedidoEntity, UUID> {
    Optional<ItemPedidoEntity> findByCodigo(UUID codigo);
    void deleteByCodigo(UUID codigo);
}