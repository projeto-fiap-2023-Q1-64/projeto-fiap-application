package br.fiap.projeto.contexto.comanda.domain.port.service;

import java.util.List;
import java.util.UUID;

import br.fiap.projeto.contexto.comanda.domain.dto.ComandaDTO;
import br.fiap.projeto.contexto.comanda.domain.dto.CriarComandaDTO;
import br.fiap.projeto.contexto.comanda.infrastructure.exception.InvalidStatusException;

public interface ComandaServicePort {

    List<ComandaDTO> buscaComandaRecebido();

    List<ComandaDTO> buscaComandaPreparacao();

    List<ComandaDTO> buscaComandaFinalizado();

    ComandaDTO criarComanda(CriarComandaDTO criarComandaDTO);

    ComandaDTO preparar(UUID codigoPedido) throws InvalidStatusException;

    ComandaDTO finalizar(UUID codigoPedido) throws InvalidStatusException, Exception;

}
