package br.fiap.projeto.contexto.identificacao.application.rest;

import br.fiap.projeto.contexto.identificacao.domain.port.dto.ClienteDTO;
import br.fiap.projeto.contexto.identificacao.domain.port.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ClienteDTO busca(Long codigo) {

        return clienteService.busca(codigo);
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
    public void remove(Long codigo) {

        clienteService.remove(codigo);
    }
}
