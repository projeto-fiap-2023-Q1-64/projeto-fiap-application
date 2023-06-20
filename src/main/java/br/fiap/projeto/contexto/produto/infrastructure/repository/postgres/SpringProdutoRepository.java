package br.fiap.projeto.contexto.produto.infrastructure.repository.postgres;

import br.fiap.projeto.contexto.produto.infrastructure.entity.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SpringProdutoRepository extends JpaRepository<ProdutoEntity, UUID> {
    Optional<ProdutoEntity> findByCodigo(UUID codigo);
}
