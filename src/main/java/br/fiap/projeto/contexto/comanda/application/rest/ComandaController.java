import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.fiap.projeto.contexto.comanda.domain.dto.ComandaDTO;
import br.fiap.projeto.contexto.comanda.domain.port.service.ComandaServicePort;

@RestController
@RequestMapping("/comandas")
public class ComandaController {

    private final ComandaServicePort comandaServicePort;

    @Autowired
    public ComandaController(ComandaServicePort comandaServicePort) {
        this.comandaServicePort = comandaServicePort;
    }

    @GetMapping("/{pendentes}")
    @ResponseBody
    ResponseEntity<List<ComandaDTO>> getComandasPendentes() {
        List<ComandaDTO> lista = this.comandaServicePort.buscaComandaPendente();
        return ResponseEntity.ok().body(lista);
    }

    @GetMapping("/{finalizadas}")
    @ResponseBody
    ResponseEntity<List<ComandaDTO>> getComandasFinalizadas() {
        List<ComandaDTO> lista = this.comandaServicePort.buscaComandaPronto();
        return ResponseEntity.ok().body(lista);
    }

    @PostMapping
    ResponseEntity<ComandaDTO> criaComanda(@RequestBody ComandaDTO ComandaDTO) {
        ComandaDTO ComandaCriado = this.comandaServicePort.criaComanda(ComandaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(ComandaCriado);
    }

    @PutMapping("/{codigo}")
    ResponseEntity<Void> atualizaComanda( @RequestBody ComandaDTO comandaDTO) {
        this.comandaServicePort.atualizaComanda(comandaDTO);
        return ResponseEntity.ok().build();
    }

   
}
