package br.fiap.projeto.contexto.comanda.usecase.port.repositoryInterface;

import br.fiap.projeto.contexto.comanda.entity.Comanda;
import br.fiap.projeto.contexto.comanda.entity.enums.StatusComanda;
import br.fiap.projeto.contexto.comanda.external.exception.ExceptionMessage;

import java.util.List;

public interface IBuscarPorStatusComandaRepositoryUseCase {
    List<Comanda> buscaComandaPorStatus(StatusComanda status) throws ExceptionMessage, Exception;
}
