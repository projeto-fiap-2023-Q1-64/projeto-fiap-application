package br.fiap.projeto.contexto.comanda.usecase;

import br.fiap.projeto.contexto.comanda.entity.Comanda;
import br.fiap.projeto.contexto.comanda.usecase.exception.ComandaNaoEncontradaException;
import br.fiap.projeto.contexto.comanda.usecase.exception.EntradaInvalidaException;
import br.fiap.projeto.contexto.comanda.usecase.port.interfaces.IBuscaPorComandaUseCase;
import br.fiap.projeto.contexto.comanda.usecase.port.repositoryInterface.IBuscarPorComandaRepositoryUseCase;

import java.util.Optional;
import java.util.UUID;

public class BuscaComandaUseCase implements IBuscaPorComandaUseCase {

    private final IBuscarPorComandaRepositoryUseCase buscarComandaRepositoryUseCase;

    public BuscaComandaUseCase(IBuscarPorComandaRepositoryUseCase buscarComandaRepositoryUseCase) {
        this.buscarComandaRepositoryUseCase = buscarComandaRepositoryUseCase;
    }

    @Override
    public Comanda buscaComandaPorStatus(UUID codigoComanda)
            throws EntradaInvalidaException, ComandaNaoEncontradaException {
        Optional<Comanda> comanda = buscarComandaRepositoryUseCase.buscar(codigoComanda);
        comanda.orElseThrow(() -> new ComandaNaoEncontradaException("Comanda não encontrada!"));
        comanda.get().validarDados();
        return comanda.get();
    }

}
