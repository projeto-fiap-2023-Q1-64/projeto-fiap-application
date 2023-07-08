package br.fiap.projeto.contexto.comanda.infrastructure.repository.postgres;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import br.fiap.projeto.contexto.comanda.domain.Comanda;
import br.fiap.projeto.contexto.comanda.domain.enums.StatusComanda;
import br.fiap.projeto.contexto.comanda.domain.port.repository.ComandaRepositoryPort;
import br.fiap.projeto.contexto.comanda.infrastructure.entity.ComandaEntity;

@Component
@Primary
public class PostgresComandaRepository implements ComandaRepositoryPort {

    private final SpringComandaRepository springComandaRepository;

    @Autowired
    public PostgresComandaRepository(SpringComandaRepository springComandaRepository) {
        this.springComandaRepository = springComandaRepository;
    }

    @Override
    public List<Comanda> buscaPorStatus(StatusComanda statusComanda) {
        List<ComandaEntity> resultados = springComandaRepository.findByStatus(statusComanda);
        return resultados.stream().map(ComandaEntity::toComanda).collect(Collectors.toList());
    }

    @Override
    public Comanda salvar(Comanda comanda) {
        ComandaEntity comandaSalva = springComandaRepository.save(new ComandaEntity(comanda));
        return comandaSalva.toComanda();
    }

    @Override
    public Optional<Comanda> buscar(UUID codigoComanda) {
        Optional<ComandaEntity> comandaEntity = springComandaRepository.findById(codigoComanda);
        if (comandaEntity.isPresent()) {
            return Optional.of(comandaEntity.get().toComanda());
        }
        return Optional.empty();
    }
}
