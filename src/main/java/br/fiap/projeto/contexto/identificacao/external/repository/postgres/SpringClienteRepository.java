package br.fiap.projeto.contexto.identificacao.external.repository.postgres;

import br.fiap.projeto.contexto.identificacao.external.repository.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SpringClienteRepository extends JpaRepository<ClienteEntity, String> {
    List<ClienteEntity> findAllByDataExclusaoIsNull();
    ClienteEntity findByCpfAndDataExclusaoIsNull(String cpf);
    ClienteEntity findByEmailAndDataExclusaoIsNull(String email);
    Optional<ClienteEntity> findByCodigoAndDataExclusaoIsNull(String codigo);
}
