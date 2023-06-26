package br.fiap.projeto.contexto.pedido.infrastructure.repository.postgres;

import br.fiap.projeto.contexto.pedido.infrastructure.entity.ProdutoPedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SpringProdutoPedidoRepository  extends JpaRepository<ProdutoPedidoEntity, UUID> {
    Optional<ProdutoPedidoEntity> findByCodigo(UUID codigo);
    void deleteByCodigo(UUID codigo);
}