package br.fiap.projeto.contexto.comanda.domain.service;

import java.util.List;
import java.util.stream.Collectors;

import br.fiap.projeto.contexto.comanda.domain.Comanda;
import br.fiap.projeto.contexto.comanda.domain.dto.ComandaDTO;
import br.fiap.projeto.contexto.comanda.domain.port.repository.ComandaRepositoryPort;
import br.fiap.projeto.contexto.comanda.domain.port.service.ComandaServicePort;

public class DomainComandaService implements ComandaServicePort {

    private final ComandaRepositoryPort comandaRepositoryPort;

    public DomainComandaService(ComandaRepositoryPort comandaRepositoryPort) {
        this.comandaRepositoryPort = comandaRepositoryPort;
    }

    @Override
    public List<ComandaDTO> buscaComandaPendente() {
        List<Comanda> comanda = comandaRepositoryPort.buscaComandaPendente();
        return comanda.stream().map(Comanda::toComandaDTO).collect(Collectors.toList());
    }

    @Override
    public ComandaDTO criaComanda(ComandaDTO comandaDTO) {
        return (comandaRepositoryPort.criaComanda(new Comanda(comandaDTO))).toComandaDTO();
    }

    @Override
    public void atualizaComanda(ComandaDTO comanda) {
        comandaRepositoryPort.atualizaComanda(new Comanda(comanda));
    }

    @Override
    public List<ComandaDTO> buscaComandaPronto() {
        List<Comanda> comandas = comandaRepositoryPort.buscaComandaPronto();
        return comandas.stream().map(Comanda::toComandaDTO).collect(Collectors.toList());
    }

   

}
