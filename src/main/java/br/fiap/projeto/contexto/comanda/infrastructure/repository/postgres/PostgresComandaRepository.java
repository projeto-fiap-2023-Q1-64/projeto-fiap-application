package br.fiap.projeto.contexto.comanda.infrastructure.repository.postgres;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import br.fiap.projeto.contexto.comanda.domain.Comanda;
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
    public List<Comanda> buscaComandaPendente() {
        List<ComandaEntity> resultados = springComandaRepository.findByStatus(2);
        return resultados.stream().map(ComandaEntity::toComanda).collect(Collectors.toList());
    }

    @Override
    public List<Comanda> buscaComandaPronto() {
        List<ComandaEntity> resultados = springComandaRepository.findByStatus(3);
        return resultados.stream().map(ComandaEntity::toComanda).collect(Collectors.toList());
    }

    @Override
    public Comanda criaComanda(Comanda comanda) {
        ComandaEntity comandaSalva = springComandaRepository.save(new ComandaEntity(comanda));
        return comandaSalva.toComanda();
     
    }

    @Override
    public void atualizaComanda(Comanda comanda) {
        Optional<ComandaEntity> comandaEntity = springComandaRepository.findByCategoria(comanda);
        comandaEntity.orElseThrow(() -> new EntityNotFoundException("Comanda não encontrado!"));
        comandaEntity.get().atualizar(comanda);
        springComandaRepository.save(comandaEntity.get());
    }

}
