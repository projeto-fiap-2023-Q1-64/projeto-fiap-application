package br.fiap.projeto.contexto.identificacao.application.rest;

import br.fiap.projeto.contexto.identificacao.domain.port.dto.ClienteDTO;
import br.fiap.projeto.contexto.identificacao.domain.port.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ClienteDTO busca(UUID codigo) {

        return clienteService.busca(codigo);
    }

    @GetMapping("/cpf")
    public ClienteDTO buscaPorCpf(String cpf) {

        return clienteService.buscaPorCpf(cpf);
    }

    @GetMapping("/todos")
    public List<ClienteDTO> buscaTodos() {

        return clienteService.buscaTodos();
    }

    @PostMapping
    public ClienteDTO insere(ClienteDTO cliente) {

        return clienteService.insere(cliente);
    }

    @PutMapping
    public ClienteDTO edita(ClienteDTO cliente) {

        return clienteService.edita(cliente);
    }

    @DeleteMapping
    public void remove(UUID codigo) {

        clienteService.remove(codigo);
    }
}
