package br.fiap.projeto.contexto.comanda.adapter.gateway;

import java.util.Optional;
import java.util.UUID;

import br.fiap.projeto.contexto.comanda.entity.Comanda;
import br.fiap.projeto.contexto.comanda.external.repository.entity.ComandaEntity;
import br.fiap.projeto.contexto.comanda.external.repository.postgres.SpringComandaRepository;
import br.fiap.projeto.contexto.comanda.usecase.exception.EntradaInvalidaException;
import br.fiap.projeto.contexto.comanda.usecase.port.repository.IBuscarPorComandaPorCodigoPedidoRepositoryUseCase;

public class BuscaPorComandaPorCodigoPedidoGatewayAdapter implements IBuscarPorComandaPorCodigoPedidoRepositoryUseCase {

    private final SpringComandaRepository springComandaRepository;

    public BuscaPorComandaPorCodigoPedidoGatewayAdapter(SpringComandaRepository springComandaRepository) {
        this.springComandaRepository = springComandaRepository;
    }

    @Override
    public Optional<Comanda> buscar(UUID codigoPedido) throws EntradaInvalidaException {
        Optional<ComandaEntity> comandaEntity = springComandaRepository.findByCodigoPedido(codigoPedido);
        return comandaEntity.map(t -> {
            try {
                return t.toComanda();
            } catch (EntradaInvalidaException e) {
                e.getMessage();
            }
            return null;
        });
    }

}
