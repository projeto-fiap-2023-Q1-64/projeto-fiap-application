package br.fiap.projeto.contexto.comanda.external.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.fiap.projeto.contexto.comanda.entity.Comanda;
import br.fiap.projeto.contexto.comanda.entity.enums.StatusComanda;
import br.fiap.projeto.contexto.comanda.external.exception.ExceptionMessage;
import br.fiap.projeto.contexto.comanda.usecase.port.repositoryInterface.IBuscarPorStatusComandaRepositoryPortUseCase;

@RestController
@RequestMapping("/comandas")
public class BuscaFinalizadoStatusComandaApiExternal {

    private final IBuscarPorStatusComandaRepositoryPortUseCase comandaServicePort;

    @Autowired
    public BuscaFinalizadoStatusComandaApiExternal(IBuscarPorStatusComandaRepositoryPortUseCase comandaServicePort) {
        this.comandaServicePort = comandaServicePort;
    }

    @GetMapping("/busca-pendentes")
    @ResponseBody
    ResponseEntity<List<Comanda>> getComandasPendentes() throws ExceptionMessage, Exception {
        List<Comanda> lista = this.comandaServicePort.buscaComandaPorStatus(StatusComanda.FINALIZADO);
        return ResponseEntity.ok().body(lista);
    }

}
