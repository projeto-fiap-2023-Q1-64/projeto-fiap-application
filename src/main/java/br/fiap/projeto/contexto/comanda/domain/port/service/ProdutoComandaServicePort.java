package br.fiap.projeto.contexto.comanda.domain.port.service;

import java.util.UUID;

import br.fiap.projeto.contexto.comanda.domain.dto.ProdutoComandaDTO;

public interface ProdutoComandaServicePort{

    ProdutoComandaDTO criaProdutoComanda(ProdutoComandaDTO produto);

    ProdutoComandaDTO buscaProdutoComanda(UUID codigo);
    
}
