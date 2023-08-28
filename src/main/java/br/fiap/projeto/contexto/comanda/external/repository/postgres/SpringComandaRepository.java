package br.fiap.projeto.contexto.comanda.external.repository.postgres;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.fiap.projeto.contexto.comanda.entity.enums.StatusComanda;
import br.fiap.projeto.contexto.comanda.external.repository.entity.ComandaEntity;

@Repository
public interface SpringComandaRepository extends JpaRepository<ComandaEntity, UUID> {
    List<ComandaEntity> findByStatus(StatusComanda statusComanda);
}
