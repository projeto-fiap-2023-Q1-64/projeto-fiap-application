package br.fiap.projeto.contexto.comanda.infrastructure.repository.postgres;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.fiap.projeto.contexto.comanda.domain.ProdutoComanda;
import br.fiap.projeto.contexto.comanda.infrastructure.entity.ProdutoComandaEntity;

public interface SpringProdutoComandaRepository extends JpaRepository<ProdutoComandaEntity, UUID> {

    ProdutoComandaEntity findByStatus(UUID codigo);

    Optional<ProdutoComandaEntity> findByProdutoComanda(ProdutoComanda produtoComanda);
}
