package br.fiap.projeto.contexto.comanda.adapter.gateway;

import br.fiap.projeto.contexto.comanda.entity.Comanda;
import br.fiap.projeto.contexto.comanda.external.repository.entity.ComandaEntity;
import br.fiap.projeto.contexto.comanda.external.repository.postgres.SpringComandaRepository;
import br.fiap.projeto.contexto.comanda.usecase.exception.EntradaInvalidaException;
import br.fiap.projeto.contexto.comanda.usecase.port.repository.IBuscarPorComandaRepositoryUseCase;

import java.util.Optional;
import java.util.UUID;

public class BuscaPorComandaGatewayAdapter implements IBuscarPorComandaRepositoryUseCase {

    private final SpringComandaRepository springComandaRepository;

    public BuscaPorComandaGatewayAdapter(SpringComandaRepository springComandaRepository) {
        this.springComandaRepository = springComandaRepository;
    }

    @Override
    public Optional<Comanda> buscar(UUID codigoPedido) throws EntradaInvalidaException {
        Optional<ComandaEntity> comandaEntity = springComandaRepository.findById(codigoPedido);
        return comandaEntity.map(ComandaEntity::toComanda);
    }

}
