package br.fiap.projeto.contexto.comanda.usecase.port.interfaces;

import java.util.UUID;

import br.fiap.projeto.contexto.comanda.entity.Comanda;
import br.fiap.projeto.contexto.comanda.usecase.exception.ComandaNaoEncontradaException;
import br.fiap.projeto.contexto.comanda.usecase.exception.EntradaInvalidaException;

public interface IBuscaPorComandaPorCodigoPedidoUseCase {
    Comanda buscaComandaPorCodigoPedido(UUID codigoPedido)
            throws EntradaInvalidaException, ComandaNaoEncontradaException;
}
