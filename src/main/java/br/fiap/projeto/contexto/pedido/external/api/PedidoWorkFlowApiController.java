package br.fiap.projeto.contexto.pedido.external.api;

import br.fiap.projeto.contexto.pedido.adapter.controller.port.IPedidoWorkFlowRestAdapterController;
import br.fiap.projeto.contexto.pedido.adapter.controller.rest.response.PedidoDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/pedidos")
@Api(tags = {"Pedido - Fluxo de Trabalho"}, description = "Endpoints para alterações de status gerais do domínio de Pedidos")
public class PedidoWorkFlowApiController {
    private final IPedidoWorkFlowRestAdapterController pedidoWorkFlowRestAdapterController;

    @Autowired
    public PedidoWorkFlowApiController(IPedidoWorkFlowRestAdapterController pedidoWorkFlowRestAdapterController) {
        this.pedidoWorkFlowRestAdapterController = pedidoWorkFlowRestAdapterController;
    }

    @PutMapping("/{codigo}/pagar")
    @ResponseBody
    @ApiOperation(value = "Pagar Pedido", notes="Esse endpoint Altera o status do pedido para Recebido para que seja enviado para pagamento.")
    public ResponseEntity<PedidoDTO> pagarPedido(@ApiParam(value = "Código do Pedido") @PathVariable("codigo") UUID codigo) throws Exception {
        return ResponseEntity.ok().body(this.pedidoWorkFlowRestAdapterController.receberPedido(codigo));
    }

    @PutMapping("/{codigo}/entregar")
    @ResponseBody
    @ApiOperation(value = "Entregar Pedido", notes="Esse endpoint Altera o status do pedido para Finalizado.")
    public ResponseEntity<PedidoDTO> entregarPedido(@ApiParam(value = "Código do Pedido") @PathVariable("codigo") UUID codigo) throws Exception {
        return ResponseEntity.ok().body(this.pedidoWorkFlowRestAdapterController.finalizarPedido(codigo));
    }

    @PutMapping("/{codigo}/cancelar")
    @ResponseBody
    @ApiOperation(value = "Cancelar Pedido", notes="Esse endpoint Altera o status do pedido para Cancelado.")
    public ResponseEntity<PedidoDTO> cancelarPedido(@ApiParam(value = "Código do Pedido") @PathVariable("codigo") UUID codigo) throws Exception {
        return ResponseEntity.ok().body(this.pedidoWorkFlowRestAdapterController.cancelarPedido(codigo));
    }
}