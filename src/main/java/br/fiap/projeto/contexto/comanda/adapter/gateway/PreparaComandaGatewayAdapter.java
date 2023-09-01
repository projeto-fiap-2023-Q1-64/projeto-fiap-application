package br.fiap.projeto.contexto.comanda.adapter.gateway;

import br.fiap.projeto.contexto.comanda.entity.Comanda;
import br.fiap.projeto.contexto.comanda.external.repository.entity.ComandaEntity;
import br.fiap.projeto.contexto.comanda.external.repository.postgres.SpringComandaRepository;
import br.fiap.projeto.contexto.comanda.usecase.exception.EntradaInvalidaException;
import br.fiap.projeto.contexto.comanda.usecase.port.repositoryInterface.IAtualizarComandaRepositoryUseCase;

import java.util.UUID;

public class PreparaComandaGatewayAdapter implements IAtualizarComandaRepositoryUseCase {

    private final SpringComandaRepository springComandaRepository;

    public PreparaComandaGatewayAdapter(SpringComandaRepository springComandaRepository) {
        this.springComandaRepository = springComandaRepository;
    }

    @Override
    public Comanda atualizar(UUID codigoComanda) throws EntradaInvalidaException {
        return springComandaRepository
                .save(new ComandaEntity(
                        new BuscaPorComandaGatewayAdapter(springComandaRepository).buscar(codigoComanda)))
                .toComanda();
    }

}
