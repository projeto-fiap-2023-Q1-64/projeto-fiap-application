package br.fiap.projeto.contexto.comanda.usecase.port.interfaces;

import br.fiap.projeto.contexto.comanda.entity.Comanda;
import br.fiap.projeto.contexto.comanda.usecase.exception.EntradaInvalidaException;

import java.util.UUID;

public interface IAtualizarComandaUseCase {
    Comanda finalizar(UUID codigoComanda) throws EntradaInvalidaException;

}
