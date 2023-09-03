package br.fiap.projeto.contexto.comanda.usecase.port.interfaces;

import br.fiap.projeto.contexto.comanda.entity.Comanda;
import br.fiap.projeto.contexto.comanda.usecase.exception.EntradaInvalidaException;

import java.util.List;

public interface IBuscaPorStatusPreparacaoComandaUseCase {
    List<Comanda> buscaComandaPorStatusPreparacao() throws EntradaInvalidaException, Exception;
}
