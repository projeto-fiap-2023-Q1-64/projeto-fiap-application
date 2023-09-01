package br.fiap.projeto.contexto.identificacao.external.api;

import br.fiap.projeto.contexto.identificacao.adapter.controller.port.IClienteRestAdapterController;
import br.fiap.projeto.contexto.identificacao.adapter.controller.rest.request.ClienteRequestDTO;
import br.fiap.projeto.contexto.identificacao.adapter.controller.rest.response.ClienteResponseDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = {"Identificação"}, description = "Endpoints do domínio de Identificação")
public class ClienteApiController {

    @Autowired
    private IClienteRestAdapterController clienteAdapterController;

    @PostMapping
    @SneakyThrows
    @ApiOperation(value = "Insere as informações de cliente", notes = "Este endpoint insere as informações de um cliente que optou por se identificar")
    public ResponseEntity<ClienteResponseDTO> insere(@RequestBody ClienteRequestDTO cliente) {
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteAdapterController.insere(cliente));
    }

    @PutMapping("/{codigo}")
    @SneakyThrows
    @ApiOperation(value = "Atualiza as informações de cliente", notes = "Este endpoint atualiza as informações de um cliente")
    public ClienteResponseDTO atualiza(@PathVariable String codigo, @RequestBody ClienteRequestDTO cliente) {
        return clienteAdapterController.atualiza(codigo, cliente);
    }

    @DeleteMapping("/{codigo}")
    @SneakyThrows
    @ApiOperation(value = "Remove o registro de cliente", notes = "Este endpoint remove o registro de um cliente")
    public void remove(@PathVariable String codigo) {
        clienteAdapterController.remove(codigo);
    }

    @GetMapping("/{codigo}")
    @SneakyThrows
    @ApiOperation(value = "Busca as informações de cliente", notes = "Este endpoint busca as informações de um cliente identificado")
    public ClienteResponseDTO busca(@PathVariable String codigo) {
        return clienteAdapterController.busca(codigo);
    }

    @GetMapping("/todos")
    @ApiOperation(value = "Busca todos os clientes", notes = "Este endpoint busca todos os clientes")
    public List<ClienteResponseDTO> buscaTodos() {
        return clienteAdapterController.buscaTodos();
    }

    @GetMapping("/cpf")
    @SneakyThrows
    @ApiOperation(value = "Busca cliente por CPF", notes = "Este endpoint busca as informações de um cliente por CPF")
    public ClienteResponseDTO buscaPorCpf(@RequestParam String cpf) {
        return clienteAdapterController.buscaPorCpf(cpf);
    }
}
