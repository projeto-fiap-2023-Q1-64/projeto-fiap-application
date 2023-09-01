package br.fiap.projeto.contexto.comanda.usecase.port.repositoryInterface;

import br.fiap.projeto.contexto.comanda.entity.Comanda;
import br.fiap.projeto.contexto.comanda.usecase.exception.EntradaInvalidaException;

import java.util.UUID;

public interface IAtualizarComandaRepositoryUseCase {

    Comanda atualizar(UUID codigoComanda) throws EntradaInvalidaException;

}
