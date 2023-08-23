package br.fiap.projeto.contexto.identificacao.external.api;

import br.fiap.projeto.contexto.identificacao.adapter.controller.port.IClienteRestAdapterController;
import br.fiap.projeto.contexto.identificacao.adapter.controller.rest.request.ClienteRequestDTO;
import br.fiap.projeto.contexto.identificacao.adapter.controller.rest.response.ClienteResponseDTO;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
@Log4j2
public class ClienteApiController {

    @Autowired
    private IClienteRestAdapterController clienteAdapterController;

    @GetMapping
    @SneakyThrows
    public ClienteResponseDTO busca(String codigo) {
        return clienteAdapterController.busca(codigo);
    }

    @GetMapping("/cpf")
    @SneakyThrows
    public ClienteResponseDTO buscaPorCpf(@RequestParam String cpf) {
        return clienteAdapterController.buscaPorCpf(cpf);
    }

    @GetMapping("/todos")
    public List<ClienteResponseDTO> buscaTodos() {
        return clienteAdapterController.buscaTodos();
    }

    @PostMapping
    @SneakyThrows
    public ResponseEntity<ClienteResponseDTO> insere(@RequestBody ClienteRequestDTO cliente) {
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteAdapterController.insere(cliente));
    }

    @PutMapping("/{codigo}")
    @SneakyThrows
    public ClienteResponseDTO edita(@PathVariable String codigo, @RequestBody ClienteRequestDTO cliente) {
        return clienteAdapterController.edita(codigo, cliente);
    }

    @DeleteMapping("/{codigo}")
    @SneakyThrows
    public void remove(@PathVariable String codigo) {
        clienteAdapterController.remove(codigo);
    }
}
