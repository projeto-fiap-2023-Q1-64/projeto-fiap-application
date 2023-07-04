package br.fiap.projeto.contexto.comanda.domain.port.service;

import java.util.List;

import br.fiap.projeto.contexto.comanda.domain.dto.ComandaDTO;

public interface ComandaServicePort {

    List<ComandaDTO> buscaComandaPendente();

    List<ComandaDTO> buscaComandaPronto();

    ComandaDTO criaComanda(ComandaDTO comandaDTO);

    void atualizaComanda(ComandaDTO comandaDTO);

}
