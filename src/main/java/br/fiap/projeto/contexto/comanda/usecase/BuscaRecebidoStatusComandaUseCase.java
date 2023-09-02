package br.fiap.projeto.contexto.comanda.usecase;

import br.fiap.projeto.contexto.comanda.entity.Comanda;
import br.fiap.projeto.contexto.comanda.entity.enums.StatusComanda;
import br.fiap.projeto.contexto.comanda.usecase.exception.EntradaInvalidaException;
import br.fiap.projeto.contexto.comanda.usecase.port.interfaces.IBuscaPorStatusRecebidoComandaUseCase;
import br.fiap.projeto.contexto.comanda.usecase.port.repository.IBuscarPorStatusComandaRepositoryUseCase;

import java.util.List;
import java.util.stream.Collectors;

public class BuscaRecebidoStatusComandaUseCase implements IBuscaPorStatusRecebidoComandaUseCase {

    private final IBuscarPorStatusComandaRepositoryUseCase buscarPorStatusComandaRepositoryUseCase;

    public BuscaRecebidoStatusComandaUseCase(
            IBuscarPorStatusComandaRepositoryUseCase buscarPorStatusComandaRepositoryUseCase) {
        this.buscarPorStatusComandaRepositoryUseCase = buscarPorStatusComandaRepositoryUseCase;
    }

    @Override
    public List<Comanda> buscaComandaPorStatusRecebido() throws EntradaInvalidaException, Exception {
        return buscarPorStatusComandaRepositoryUseCase.buscaComandaPorStatus(StatusComanda.RECEBIDO).stream()
                .collect(Collectors.toList());
    }

}
