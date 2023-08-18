package br.fiap.projeto.contexto.produto.external.repository.postgres;

import br.fiap.projeto.contexto.produto.entity.enums.CategoriaProduto;
import br.fiap.projeto.contexto.produto.external.repository.entity.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SpringProdutoRepository extends JpaRepository<ProdutoEntity, UUID> {
    Optional<ProdutoEntity> findByCodigo(String codigo);

    List<ProdutoEntity> findByCategoria(CategoriaProduto categoria);

    void deleteByCodigo(String codigo);
}
