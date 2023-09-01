package br.fiap.projeto.contexto.comanda.adapter.gateway;

import br.fiap.projeto.contexto.comanda.entity.Comanda;
import br.fiap.projeto.contexto.comanda.external.repository.entity.ComandaEntity;
import br.fiap.projeto.contexto.comanda.external.repository.postgres.SpringComandaRepository;
import br.fiap.projeto.contexto.comanda.usecase.port.repositoryInterface.IAtualizarComandaRepositoryUseCase;

public class FinalizaComandaGatewayAdapter implements IAtualizarComandaRepositoryUseCase {

    private final SpringComandaRepository springComandaRepository;

    public FinalizaComandaGatewayAdapter(SpringComandaRepository springComandaRepository) {
        this.springComandaRepository = springComandaRepository;
    }

    @Override
    public Comanda atualizar(Comanda comanda) {
        return springComandaRepository.save(new ComandaEntity(comanda)).toComanda();
    }

}
