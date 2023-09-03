package br.fiap.projeto.contexto.comanda.usecase.port.repository;

import br.fiap.projeto.contexto.comanda.entity.Comanda;
import br.fiap.projeto.contexto.comanda.usecase.exception.EntradaInvalidaException;

import java.util.Optional;
import java.util.UUID;

public interface IBuscarPorComandaRepositoryUseCase {

    Optional<Comanda> buscar(UUID codigoPedido) throws EntradaInvalidaException;

}
