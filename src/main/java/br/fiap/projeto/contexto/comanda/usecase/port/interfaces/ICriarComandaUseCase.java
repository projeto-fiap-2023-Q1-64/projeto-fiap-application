package br.fiap.projeto.contexto.comanda.usecase.port.interfaces;

import java.util.UUID;

import br.fiap.projeto.contexto.comanda.entity.Comanda;
import br.fiap.projeto.contexto.comanda.usecase.exception.ComandaDuplicadaException;
import br.fiap.projeto.contexto.comanda.usecase.exception.EntradaInvalidaException;

public interface ICriarComandaUseCase {

    Comanda criarComanda(UUID codigoPedido)
            throws EntradaInvalidaException, ComandaDuplicadaException;

}
