package br.fiap.projeto.contexto.pedido.external.api;

import br.fiap.projeto.contexto.pedido.adapter.controller.port.IPedidoWorkFlowRestAdapterController;
import br.fiap.projeto.contexto.pedido.adapter.controller.rest.response.PedidoDTO;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/pedidos")
public class PedidoWorkFlowApiController {
    private final IPedidoWorkFlowRestAdapterController pedidoWorkFlowRestAdapterController;

    @Autowired
    public PedidoWorkFlowApiController(IPedidoWorkFlowRestAdapterController pedidoWorkFlowRestAdapterController) {
        this.pedidoWorkFlowRestAdapterController = pedidoWorkFlowRestAdapterController;
    }

    @PutMapping("/{codigo}/pagar")
    @ResponseBody
    public ResponseEntity<PedidoDTO> pagarPedido(@ApiParam(value = "Código do Pedido") @PathVariable("codigo") UUID codigo) throws Exception {
        return ResponseEntity.ok().body(this.pedidoWorkFlowRestAdapterController.receberPedido(codigo));
    }

    @PutMapping("/{codigo}/entregar")
    @ResponseBody
    public ResponseEntity<PedidoDTO> entregarPedido(@ApiParam(value = "Código do Pedido") @PathVariable("codigo") UUID codigo) throws Exception {
        return ResponseEntity.ok().body(this.pedidoWorkFlowRestAdapterController.finalizarPedido(codigo));
    }

    @PutMapping("/{codigo}/cancelar")
    @ResponseBody
    public ResponseEntity<PedidoDTO> cancelarPedido(@ApiParam(value = "Código do Pedido") @PathVariable("codigo") UUID codigo) throws Exception {
        return ResponseEntity.ok().body(this.pedidoWorkFlowRestAdapterController.cancelarPedido(codigo));
    }
}