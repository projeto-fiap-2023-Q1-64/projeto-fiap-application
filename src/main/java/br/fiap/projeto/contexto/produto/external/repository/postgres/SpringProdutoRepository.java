package br.fiap.projeto.contexto.produto.external.repository.postgres;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.fiap.projeto.contexto.produto.entity.enums.CategoriaProduto;
import br.fiap.projeto.contexto.produto.external.repository.entity.ProdutoEntity;

@Repository
public interface SpringProdutoRepository extends JpaRepository<ProdutoEntity, String> {
    Optional<ProdutoEntity> findByCodigo(String codigo);

    List<ProdutoEntity> findByCategoria(CategoriaProduto categoria);

    void deleteByCodigo(String codigo);
}
