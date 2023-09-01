package br.fiap.projeto.contexto.pagamento.external.api;

import br.fiap.projeto.contexto.pagamento.adapter.controller.rest.port.IProcessaPagamentoRestAdapterController;
import br.fiap.projeto.contexto.pagamento.adapter.controller.rest.request.PedidoAPagarDTORequest;
import br.fiap.projeto.contexto.pagamento.adapter.controller.rest.response.PagamentoNovoDTOResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = {"Pagamentos"}, description = "Endpoints do domínio de Pagamentos")
public class PagamentoProcessaNovoApiController {

    private final IProcessaPagamentoRestAdapterController processaPagamentoRestAdapterController;

    @Autowired
    public PagamentoProcessaNovoApiController(IProcessaPagamentoRestAdapterController processaPagamentoRestAdapterController) {
        this.processaPagamentoRestAdapterController = processaPagamentoRestAdapterController;
    }

    @PostMapping(value="/novo-pagamento")
    @Transactional
    @ApiOperation(value = "Inicia um novo pagamento com o código do pedido", notes="Esse endppoint permite a criação de um novo pagamento a partir do código do Pedido.")
    public ResponseEntity<PagamentoNovoDTOResponse> iniciaPagamento(@RequestBody PedidoAPagarDTORequest pedidoAPagarDTORequest) {
        PagamentoNovoDTOResponse pagamentoDTOResponse = processaPagamentoRestAdapterController.criaNovoPagamento(pedidoAPagarDTORequest);
        URI novoRecursoDePagamentoCriadoUri = ServletUriComponentsBuilder.fromCurrentRequest().path("{/codigo}").buildAndExpand(pedidoAPagarDTORequest.getCodigoPedido()).toUri();
        return ResponseEntity.created(novoRecursoDePagamentoCriadoUri).body(pagamentoDTOResponse);
    }

}
