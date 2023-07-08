package br.fiap.projeto.contexto.comanda.domain.port.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import br.fiap.projeto.contexto.comanda.domain.Comanda;
import br.fiap.projeto.contexto.comanda.domain.enums.StatusComanda;

public interface ComandaRepositoryPort {

    List<Comanda> buscaPorStatus(StatusComanda statusComanda);

    Comanda salvar(Comanda comanda);

    Optional<Comanda> buscar(UUID codigoComanda);

}
