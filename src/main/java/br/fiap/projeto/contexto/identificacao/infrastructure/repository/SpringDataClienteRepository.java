package br.fiap.projeto.contexto.identificacao.infrastructure.repository;

import br.fiap.projeto.contexto.identificacao.infrastructure.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataClienteRepository extends JpaRepository<ClienteEntity, Long> {

}
