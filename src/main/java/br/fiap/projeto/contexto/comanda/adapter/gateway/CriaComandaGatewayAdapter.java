package br.fiap.projeto.contexto.comanda.adapter.gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import br.fiap.projeto.contexto.comanda.entity.Comanda;
import br.fiap.projeto.contexto.comanda.external.exception.ExceptionMessage;
import br.fiap.projeto.contexto.comanda.external.repository.entity.ComandaEntity;
import br.fiap.projeto.contexto.comanda.external.repository.postgres.SpringComandaRepository;
import br.fiap.projeto.contexto.comanda.usecase.port.repositoryInterface.ICriarComandaRepositoryPortUseCase;

@Component
@Primary
public class CriaComandaGatewayAdapter implements ICriarComandaRepositoryPortUseCase {

    private final SpringComandaRepository springComandaRepository;

    @Autowired
    public CriaComandaGatewayAdapter(SpringComandaRepository springComandaRepository) {
        this.springComandaRepository = springComandaRepository;
    }

    @Override
    public Comanda criar(Comanda comanda) throws ExceptionMessage {
        return springComandaRepository.save(new ComandaEntity(comanda)).toComanda();
    }

}
