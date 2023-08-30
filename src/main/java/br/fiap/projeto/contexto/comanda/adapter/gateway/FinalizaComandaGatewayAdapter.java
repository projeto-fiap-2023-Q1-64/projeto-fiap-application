package br.fiap.projeto.contexto.comanda.adapter.gateway;

import br.fiap.projeto.contexto.comanda.entity.Comanda;
import br.fiap.projeto.contexto.comanda.external.exception.ExceptionMessage;
import br.fiap.projeto.contexto.comanda.external.repository.entity.ComandaEntity;
import br.fiap.projeto.contexto.comanda.external.repository.postgres.SpringComandaRepository;
import br.fiap.projeto.contexto.comanda.usecase.port.repositoryInterface.IAtualizarComandaRepositoryUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.UUID;

public class FinalizaComandaGatewayAdapter implements IAtualizarComandaRepositoryUseCase {

    private final SpringComandaRepository springComandaRepository;

    public FinalizaComandaGatewayAdapter(SpringComandaRepository springComandaRepository) {
        this.springComandaRepository = springComandaRepository;
    }

    @Override
    public Comanda atualizar(UUID codigoComanda) throws ExceptionMessage {
        return springComandaRepository
                .save(new ComandaEntity(
                        new BuscaPorComandaGatewayAdapter(springComandaRepository).buscar(codigoComanda)))
                .toComanda();
    }

}
