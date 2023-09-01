package br.fiap.projeto.contexto.comanda.usecase.port.repositoryInterface;

import br.fiap.projeto.contexto.comanda.entity.Comanda;
import br.fiap.projeto.contexto.comanda.usecase.exception.EntradaInvalidaException;

public interface ICriarComandaRepositoryUseCase {

    Comanda criar(Comanda comanda) throws EntradaInvalidaException;

}
