package br.fiap.projeto.contexto.comanda.usecase.port.repositoryInterface;

import java.util.Optional;
import java.util.UUID;

import br.fiap.projeto.contexto.comanda.entity.Comanda;
import br.fiap.projeto.contexto.comanda.external.exception.ExceptionMessage;

public interface IBuscarComandaRepositoryPortUseCase {

    Optional<Comanda> buscar(UUID codigoComanda) throws ExceptionMessage;

}
