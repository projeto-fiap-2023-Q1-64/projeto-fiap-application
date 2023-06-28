package br.fiap.projeto.contexto.identificacao.application.rest;

import br.fiap.projeto.contexto.identificacao.application.rest.response.ClienteDTO;
import br.fiap.projeto.contexto.identificacao.domain.port.service.ClienteService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
@Log4j2
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ClienteDTO busca(String codigo) {

        return clienteService.busca(codigo);
    }

    @GetMapping("/cpf")
    public ClienteDTO buscaPorCpf(@RequestParam String cpf) {

        return clienteService.buscaPorCpf(cpf);
    }

    @GetMapping("/todos")
    public List<ClienteDTO> buscaTodos() {

        return clienteService.buscaTodos();
    }

    @PostMapping
    public ResponseEntity<ClienteDTO> insere(@RequestBody ClienteDTO cliente) {

        return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.insere(cliente));
    }

    @PutMapping
    public ClienteDTO edita(@RequestBody ClienteDTO cliente) {

        return clienteService.edita(cliente);
    }

    @DeleteMapping
    public void remove(@RequestParam String codigo) {

        clienteService.remove(codigo);
    }
}
