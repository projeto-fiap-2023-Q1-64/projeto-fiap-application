package br.fiap.projeto.contexto.comanda.application.rest;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.fiap.projeto.contexto.comanda.domain.dto.ComandaDTO;
import br.fiap.projeto.contexto.comanda.domain.dto.CriarComandaDTO;
import br.fiap.projeto.contexto.comanda.domain.exception.InvalidStatusException;
import br.fiap.projeto.contexto.comanda.domain.port.service.ComandaServicePort;

@RestController
@RequestMapping("/comandas")
public class ComandaController {

    private final ComandaServicePort comandaServicePort;

    @Autowired
    public ComandaController(ComandaServicePort comandaServicePort) {
        this.comandaServicePort = comandaServicePort;
    }

    @PostMapping
    ResponseEntity<ComandaDTO> criaComanda(@RequestBody CriarComandaDTO criarComandaDTO) {
        ComandaDTO comandaCriado = this.comandaServicePort.criarComanda(criarComandaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(comandaCriado);
    }

    // @PutMapping("/{codigo}")
    // ResponseEntity<Void> atualizaComanda(@RequestBody ComandaDTO comandaDTO) {
    // this.comandaServicePort.atualizaComanda(comandaDTO);
    // return ResponseEntity.ok().build();
    // }

    // RECEBIDO, EM_PREPARACAO, FINALIZADO

    @GetMapping("busca-pendentes")
    @ResponseBody
    ResponseEntity<List<ComandaDTO>> getComandasPendentes() {
        List<ComandaDTO> lista = this.comandaServicePort.buscaComandaRecebido();
        return ResponseEntity.ok().body(lista);
    }

    @GetMapping("busca-em-preparacao")
    @ResponseBody
    ResponseEntity<List<ComandaDTO>> getComandasPreapracao() {
        List<ComandaDTO> lista = this.comandaServicePort.buscaComandaPreparacao();
        return ResponseEntity.ok().body(lista);
    }

    @GetMapping("busca-finalizadas")
    @ResponseBody
    ResponseEntity<List<ComandaDTO>> getComandasFinalizadas() {
        List<ComandaDTO> lista = this.comandaServicePort.buscaComandaFinalizado();
        return ResponseEntity.ok().body(lista);
    }

    @PatchMapping("/{codigo-comanda}/preparar")
    @ResponseBody
    ResponseEntity<ComandaDTO> preparar(@PathVariable("codigo-comanda") UUID codigoComando)
            throws InvalidStatusException {
        ComandaDTO prepararComandaDTO = this.comandaServicePort.preparar(codigoComando);
        return ResponseEntity.status(HttpStatus.CREATED).body(prepararComandaDTO);
    }

    @PatchMapping("/{codigo-comanda}/finalizar")
    @ResponseBody
    ResponseEntity<ComandaDTO> finalizar(@PathVariable("codigo-comanda") UUID codigoComando)
            throws InvalidStatusException {
        ComandaDTO finalizarComandaDTO = this.comandaServicePort.finalizar(codigoComando);
        return ResponseEntity.status(HttpStatus.CREATED).body(finalizarComandaDTO);
    }

}
