package br.fiap.projeto.contexto.comanda.usecase.port.interfaces;

import java.util.UUID;

import br.fiap.projeto.contexto.comanda.entity.Comanda;
import br.fiap.projeto.contexto.comanda.external.exception.ExceptionMessage;

public interface ICriarComandaPortUseCase {

    Comanda criarComanda(UUID codigoPedido) throws ExceptionMessage;

}
