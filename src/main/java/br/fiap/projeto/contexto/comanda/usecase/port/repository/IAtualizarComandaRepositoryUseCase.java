package br.fiap.projeto.contexto.comanda.usecase.port.repository;

import br.fiap.projeto.contexto.comanda.entity.Comanda;
import br.fiap.projeto.contexto.comanda.usecase.exception.EntradaInvalidaException;

public interface IAtualizarComandaRepositoryUseCase {

    Comanda atualizar(Comanda comanda) throws EntradaInvalidaException;

}
