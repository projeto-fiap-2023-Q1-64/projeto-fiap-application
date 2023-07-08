package br.fiap.projeto.contexto.comanda.infrastructure.repository.postgres;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.fiap.projeto.contexto.comanda.domain.enums.StatusComanda;
import br.fiap.projeto.contexto.comanda.infrastructure.entity.ComandaEntity;

@Repository
public interface SpringComandaRepository extends JpaRepository<ComandaEntity, UUID> {
    List<ComandaEntity> findByStatus(StatusComanda statusComanda);
}
