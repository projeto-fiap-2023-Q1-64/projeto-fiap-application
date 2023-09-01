package br.fiap.projeto.contexto.comanda.usecase.port.repositoryInterface;

import java.util.Optional;
import java.util.UUID;

import br.fiap.projeto.contexto.comanda.entity.Comanda;
import br.fiap.projeto.contexto.comanda.usecase.exception.EntradaInvalidaException;

public interface IBuscarPorComandaPorCodigoPedidoRepositoryUseCase {

    Optional<Comanda> buscar(UUID codigoPedido) throws EntradaInvalidaException;

}
