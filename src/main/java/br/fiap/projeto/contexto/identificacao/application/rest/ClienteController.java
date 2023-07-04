package br.fiap.projeto.contexto.identificacao.application.rest;

import br.fiap.projeto.contexto.identificacao.application.rest.request.ClienteRequestDTO;
import br.fiap.projeto.contexto.identificacao.application.rest.response.ClienteResponseDTO;
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
    public ClienteResponseDTO busca(String codigo) {

        return clienteService.busca(codigo);
    }

    @GetMapping("/cpf")
    public ClienteResponseDTO buscaPorCpf(@RequestParam String cpf) {

        return clienteService.buscaPorCpf(cpf);
    }

    @GetMapping("/todos")
    public List<ClienteResponseDTO> buscaTodos() {

        return clienteService.buscaTodos();
    }

    @PostMapping
    public ResponseEntity<ClienteResponseDTO> insere(@RequestBody ClienteRequestDTO cliente) {

        return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.insere(cliente));
    }

    @PutMapping
    public ClienteResponseDTO edita(@RequestBody ClienteResponseDTO cliente) {

        return clienteService.edita(cliente);
    }

    @DeleteMapping
    public void remove(@RequestParam String codigo) {

        clienteService.remove(codigo);
    }
}
