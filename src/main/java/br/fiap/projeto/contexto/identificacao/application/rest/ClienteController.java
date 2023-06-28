package br.fiap.projeto.contexto.identificacao.application.rest;

import br.fiap.projeto.contexto.identificacao.application.entity.MensagemErroDTO;
import br.fiap.projeto.contexto.identificacao.domain.port.dto.ClienteDTO;
import br.fiap.projeto.contexto.identificacao.domain.port.service.ClienteService;
import br.fiap.projeto.contexto.identificacao.infrastructure.exception.BaseException;
import br.fiap.projeto.contexto.identificacao.infrastructure.exception.EntidadeNaoEncontradaException;
import br.fiap.projeto.contexto.identificacao.infrastructure.exception.EntradaInvalidaException;
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

    @ExceptionHandler({EntidadeNaoEncontradaException.class})
    public ResponseEntity<MensagemErroDTO> entidadeNaoEncontradaHandler(EntidadeNaoEncontradaException ex) {

        ResponseEntity<MensagemErroDTO> erro = ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MensagemErroDTO(ex.getCode(), ex.getMessage()));
        return erro;
    }

    @ExceptionHandler({EntradaInvalidaException.class})
    public ResponseEntity<MensagemErroDTO> entradaInvalidaHandler(EntradaInvalidaException ex) {

        ResponseEntity<MensagemErroDTO> erro = ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(new MensagemErroDTO(ex.getCode(), ex.getMessage()));
        return erro;
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<MensagemErroDTO> genericHandler(EntradaInvalidaException ex) {

        ResponseEntity<MensagemErroDTO> erro = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new MensagemErroDTO(BaseException.getCode(), ex.getMessage()));

        log.error("Exceção não tratada: ", ex);
        return erro;
    }
}
