package br.fiap.projeto.contexto.comanda.usecase.port.repositoryInterface;

import br.fiap.projeto.contexto.comanda.entity.Comanda;
import br.fiap.projeto.contexto.comanda.external.exception.ExceptionMessage;

import java.util.UUID;

public interface IAtualizarComandaRepositoryUseCase {

    Comanda atualizar(UUID codigoComanda) throws ExceptionMessage;

}
