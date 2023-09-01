package br.fiap.projeto.contexto.comanda.adapter.gateway;

import br.fiap.projeto.contexto.comanda.entity.Comanda;
import br.fiap.projeto.contexto.comanda.entity.enums.StatusComanda;
import br.fiap.projeto.contexto.comanda.external.repository.entity.ComandaEntity;
import br.fiap.projeto.contexto.comanda.external.repository.postgres.SpringComandaRepository;
import br.fiap.projeto.contexto.comanda.usecase.port.repositoryInterface.IBuscarPorStatusComandaRepositoryUseCase;

import java.util.List;
import java.util.stream.Collectors;

public class BuscaPorStatusComandaGatewayAdapter implements IBuscarPorStatusComandaRepositoryUseCase {

    private final SpringComandaRepository springComandaRepository;

    public BuscaPorStatusComandaGatewayAdapter(SpringComandaRepository springComandaRepository) {
        this.springComandaRepository = springComandaRepository;
    }

    @Override
    public List<Comanda> buscaComandaPorStatus(StatusComanda status) {
        return springComandaRepository.findByStatus(status).stream().map(ComandaEntity::toComanda)
                .collect(Collectors.toList());
    }

}
