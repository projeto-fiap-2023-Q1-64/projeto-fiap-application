package br.fiap.projeto.contexto.comanda.adapter.gateway;

import br.fiap.projeto.contexto.comanda.entity.Comanda;
import br.fiap.projeto.contexto.comanda.external.repository.entity.ComandaEntity;
import br.fiap.projeto.contexto.comanda.external.repository.postgres.SpringComandaRepository;
import br.fiap.projeto.contexto.comanda.usecase.exception.EntradaInvalidaException;
import br.fiap.projeto.contexto.comanda.usecase.port.repositoryInterface.ICriarComandaRepositoryUseCase;

public class CriaComandaGatewayAdapter implements ICriarComandaRepositoryUseCase {

    private final SpringComandaRepository springComandaRepository;

    public CriaComandaGatewayAdapter(SpringComandaRepository springComandaRepository) {
        this.springComandaRepository = springComandaRepository;
    }

    @Override
    public Comanda criar(Comanda comanda) throws EntradaInvalidaException {
        return springComandaRepository.save(new ComandaEntity(comanda)).toComanda();
    }

}
