package br.fiap.projeto.contexto.comanda.usecase.port.repository;

import br.fiap.projeto.contexto.comanda.entity.Comanda;
import br.fiap.projeto.contexto.comanda.entity.enums.StatusComanda;
import br.fiap.projeto.contexto.comanda.usecase.exception.EntradaInvalidaException;

import java.util.List;

public interface IBuscarPorStatusComandaRepositoryUseCase {
    List<Comanda> buscaComandaPorStatus(StatusComanda status) throws EntradaInvalidaException, Exception;
}
