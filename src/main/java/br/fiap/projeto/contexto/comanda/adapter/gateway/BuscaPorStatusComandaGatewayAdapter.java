package br.fiap.projeto.contexto.comanda.adapter.gateway;

import br.fiap.projeto.contexto.comanda.entity.Comanda;
import br.fiap.projeto.contexto.comanda.entity.enums.StatusComanda;
import br.fiap.projeto.contexto.comanda.external.exception.ExceptionMessage;
import br.fiap.projeto.contexto.comanda.external.repository.entity.ComandaEntity;
import br.fiap.projeto.contexto.comanda.external.repository.postgres.SpringComandaRepository;
import br.fiap.projeto.contexto.comanda.usecase.port.repositoryInterface.IBuscarPorStatusComandaRepositoryUseCase;

import java.util.ArrayList;
import java.util.List;

public class BuscaPorStatusComandaGatewayAdapter implements IBuscarPorStatusComandaRepositoryUseCase {

    private final SpringComandaRepository springComandaRepository;

    public BuscaPorStatusComandaGatewayAdapter(SpringComandaRepository springComandaRepository) {
        this.springComandaRepository = springComandaRepository;
    }

    @Override
    public List<Comanda> buscaComandaPorStatus(StatusComanda status) throws ExceptionMessage {
        List<ComandaEntity> resultados = springComandaRepository.findByStatus(status);
        List<Comanda> comandas = new ArrayList<>();
        if (!resultados.isEmpty()) {
            for (ComandaEntity comandaEntity : resultados) {
                comandas.add(comandaEntity.toComanda());
            }
            return comandas;
        }
        return null;
    }

}
