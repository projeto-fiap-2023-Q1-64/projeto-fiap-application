package br.fiap.projeto.contexto.comanda.usecase.port.interfaces;

import br.fiap.projeto.contexto.comanda.entity.Comanda;
import br.fiap.projeto.contexto.comanda.external.exception.ExceptionMessage;

import java.util.UUID;

public interface ICriarComandaUseCase {

    Comanda criarComanda(UUID codigoPedido) throws ExceptionMessage;

}
