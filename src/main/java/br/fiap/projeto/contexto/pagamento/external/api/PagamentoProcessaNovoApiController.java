package br.fiap.projeto.contexto.pagamento.external.api;

import br.fiap.projeto.contexto.pagamento.adapter.controller.rest.port.IProcessaPagamentoRestAdapterController;
import br.fiap.projeto.contexto.pagamento.adapter.controller.rest.request.PedidoAPagarDTORequest;
import br.fiap.projeto.contexto.pagamento.adapter.controller.rest.response.PagamentoNovoDTOResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/pagamento/processa")
public class PagamentoProcessaNovoApiController {

    private final IProcessaPagamentoRestAdapterController processaPagamentoRestAdapterController;

    @Autowired
    public PagamentoProcessaNovoApiController(IProcessaPagamentoRestAdapterController processaPagamentoRestAdapterController) {
        this.processaPagamentoRestAdapterController = processaPagamentoRestAdapterController;
    }

    @PostMapping(value="/novo-pagamento")
    @Transactional
    public ResponseEntity<PagamentoNovoDTOResponse> iniciaPagamento(@RequestBody PedidoAPagarDTORequest pedidoAPagarDTORequest) {
        PagamentoNovoDTOResponse pagamentoDTOResponse = processaPagamentoRestAdapterController.criaNovoPagamento(pedidoAPagarDTORequest);
        URI novoRecursoDePagamentoCriadoUri = ServletUriComponentsBuilder.fromCurrentRequest().path("{/codigo}").buildAndExpand(pedidoAPagarDTORequest.getCodigoPedido()).toUri();
        return ResponseEntity.created(novoRecursoDePagamentoCriadoUri).body(pagamentoDTOResponse);
    }

}