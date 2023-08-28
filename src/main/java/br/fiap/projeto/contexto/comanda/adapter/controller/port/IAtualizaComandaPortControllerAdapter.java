package br.fiap.projeto.contexto.comanda.adapter.controller.port;

import java.util.UUID;

import br.fiap.projeto.contexto.comanda.adapter.controller.rest.dto.ComandaDTO;

public interface IAtualizaComandaPortControllerAdapter {

    ComandaDTO atualizaStatusComanda(UUID codigoComanda)
            throws Exception;
}
