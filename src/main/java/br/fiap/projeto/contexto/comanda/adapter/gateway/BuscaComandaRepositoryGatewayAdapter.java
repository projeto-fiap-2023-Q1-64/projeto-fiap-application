package br.fiap.projeto.contexto.comanda.adapter.gateway;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import br.fiap.projeto.contexto.comanda.entity.Comanda;
import br.fiap.projeto.contexto.comanda.external.exception.ExceptionMessage;
import br.fiap.projeto.contexto.comanda.external.repository.entity.ComandaEntity;
import br.fiap.projeto.contexto.comanda.external.repository.postgres.SpringComandaRepository;
import br.fiap.projeto.contexto.comanda.usecase.port.repositoryInterface.IBuscarComandaRepositoryPortUseCase;

@Component
@Primary
public class BuscaComandaRepositoryGatewayAdapter implements IBuscarComandaRepositoryPortUseCase {

    private final SpringComandaRepository springComandaRepository;

    @Autowired
    public BuscaComandaRepositoryGatewayAdapter(SpringComandaRepository springComandaRepository) {
        this.springComandaRepository = springComandaRepository;
    }

    @Override
    public Optional<Comanda> buscar(UUID codigoComanda) throws ExceptionMessage {
        Optional<ComandaEntity> comandaEntity = springComandaRepository.findById(codigoComanda);
        if (comandaEntity.isPresent()) {
            return Optional.of(comandaEntity.get().toComanda());
        }
        return Optional.empty();
    }
}
