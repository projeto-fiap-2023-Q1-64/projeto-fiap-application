package br.fiap.projeto.contexto.comanda.external.api;

import br.fiap.projeto.contexto.comanda.adapter.controller.port.IBuscaPorStatusFinalizadoComandaControllerAdapter;
import br.fiap.projeto.contexto.comanda.adapter.controller.rest.dto.ComandaDTO;
import br.fiap.projeto.contexto.comanda.entity.Comanda;
import br.fiap.projeto.contexto.comanda.external.exception.ExceptionMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/comandas")
public class BuscaFinalizadoStatusComandaApiExternal {

    private final IBuscaPorStatusFinalizadoComandaControllerAdapter buscaFinalizadoStatusComandaControllerAdapter;

    @Autowired
    public BuscaFinalizadoStatusComandaApiExternal(IBuscaPorStatusFinalizadoComandaControllerAdapter buscaFinalizadoStatusComandaControllerAdapter) {
        this.buscaFinalizadoStatusComandaControllerAdapter = buscaFinalizadoStatusComandaControllerAdapter;
    }

    @GetMapping("/busca-finalizado")
    @ResponseBody
    ResponseEntity<List<ComandaDTO>> getComandasPendentes() throws ExceptionMessage, Exception {
        List<ComandaDTO> lista = this.buscaFinalizadoStatusComandaControllerAdapter.buscaPorStatusFinalizado();
        return ResponseEntity.ok().body(lista);
    }

}
