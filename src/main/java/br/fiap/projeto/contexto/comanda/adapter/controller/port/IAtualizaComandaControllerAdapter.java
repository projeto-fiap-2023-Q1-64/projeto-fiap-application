package br.fiap.projeto.contexto.comanda.adapter.controller.port;

import br.fiap.projeto.contexto.comanda.adapter.controller.rest.dto.ComandaDTO;

import java.util.UUID;

public interface IAtualizaComandaControllerAdapter {

    ComandaDTO atualizaStatusComanda(UUID codigoComanda)
            throws Exception;
}
