package br.fiap.projeto.contexto.comanda.infrastructure.repository.postgres;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.fiap.projeto.contexto.comanda.infrastructure.entity.ItemComandaEntity;

@Repository
public interface SpringItemComandaRepository extends JpaRepository<ItemComandaEntity, UUID> {

    List<ItemComandaEntity> findByStatus(UUID codigoPedido, UUID codigoProduto);

    // Optional<ComandaEntity> findByItemComanda(ItemComanda itemComanda);
}
