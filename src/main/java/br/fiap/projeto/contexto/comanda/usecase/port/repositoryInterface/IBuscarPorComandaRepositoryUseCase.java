package br.fiap.projeto.contexto.comanda.usecase.port.repositoryInterface;

import br.fiap.projeto.contexto.comanda.entity.Comanda;
import br.fiap.projeto.contexto.comanda.usecase.exception.EntradaInvalidaException;

import java.util.UUID;

public interface IBuscarPorComandaRepositoryUseCase {

    Comanda buscar(UUID codigoComanda) throws EntradaInvalidaException;

}
