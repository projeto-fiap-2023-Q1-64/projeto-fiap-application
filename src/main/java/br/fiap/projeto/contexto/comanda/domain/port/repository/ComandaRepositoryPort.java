package br.fiap.projeto.contexto.comanda.domain.port.repository;

import java.util.List;

import br.fiap.projeto.contexto.comanda.domain.Comanda;

public interface ComandaRepositoryPort {

    List<Comanda> buscaComandaPendente();

    List<Comanda> buscaComandaPronto();

    Comanda criaComanda(Comanda comanda);

    void atualizaComanda(Comanda comanda);

}
