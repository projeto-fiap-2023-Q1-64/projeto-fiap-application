package br.fiap.projeto.contexto.comanda.infrastructure.repository.postgres;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.fiap.projeto.contexto.comanda.domain.Comanda;
import br.fiap.projeto.contexto.comanda.infrastructure.entity.ComandaEntity;

@Repository
public interface SpringComandaRepository extends JpaRepository<ComandaEntity, UUID> {
    List<ComandaEntity> findByStatus(int posicao);

    Optional<ComandaEntity> findByCategoria(Comanda comanda);
}
