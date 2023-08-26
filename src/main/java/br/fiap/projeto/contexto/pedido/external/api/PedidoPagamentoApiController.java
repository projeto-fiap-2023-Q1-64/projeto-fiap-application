package br.fiap.projeto.contexto.pedido.external.api;

import br.fiap.projeto.contexto.pedido.adapter.controller.port.IPedidoPagamentoIntegrationRestAdapterController;
import br.fiap.projeto.contexto.pedido.adapter.controller.rest.response.PedidoDTO;
import br.fiap.projeto.contexto.pedido.entity.enums.StatusPagamento;
import br.fiap.projeto.contexto.pedido.external.integration.port.Pagamento;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/pedidos")
public class PedidoPagamentoApiController {
    private final IPedidoPagamentoIntegrationRestAdapterController pedidoPagamentoIntegrationRestAdapterController;

    @Autowired
    public PedidoPagamentoApiController(IPedidoPagamentoIntegrationRestAdapterController pedidoPagamentoIntegrationRestAdapterController) {
        this.pedidoPagamentoIntegrationRestAdapterController = pedidoPagamentoIntegrationRestAdapterController;
    }


    @PatchMapping("/{codigo}/verificar-pagamento")
    @ResponseBody
    public ResponseEntity<?> verificarPagamento(@ApiParam(value = "Código do Pedido") @PathVariable("codigo") UUID codigo) throws Exception {
        return ResponseEntity.ok().body(pedidoPagamentoIntegrationRestAdapterController.pagar(codigo));
    }
}