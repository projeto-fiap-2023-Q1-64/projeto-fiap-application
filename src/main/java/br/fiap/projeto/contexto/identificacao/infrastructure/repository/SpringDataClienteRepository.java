package br.fiap.projeto.contexto.identificacao.infrastructure.repository;

import br.fiap.projeto.contexto.identificacao.infrastructure.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SpringDataClienteRepository extends JpaRepository<ClienteEntity, UUID> {

    List<ClienteEntity> findAllByDataExclusaoIsNull();
    ClienteEntity findByCpfAndDataExclusaoIsNull(String cpf);
    Optional<ClienteEntity> findByCodigoAndDataExclusaoIsNull(UUID codigo);
}
