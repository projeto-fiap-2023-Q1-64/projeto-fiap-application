package br.fiap.projeto.contexto.pedido.external.api;

import br.fiap.projeto.contexto.pedido.adapter.controller.port.IPedidoComandaIntegrationRestAdapterController;
import br.fiap.projeto.contexto.pedido.adapter.controller.port.IPedidoWorkFlowRestAdapterController;
import br.fiap.projeto.contexto.pedido.adapter.controller.rest.response.PedidoDTO;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/pedidos")
public class PedidoComandaApiController {
    private final IPedidoWorkFlowRestAdapterController pedidoWorkFlowRestAdapterController;
    private final IPedidoComandaIntegrationRestAdapterController pedidoComandaIntegrationRestAdapterController;

    @Autowired
    public PedidoComandaApiController(IPedidoWorkFlowRestAdapterController pedidoWorkFlowRestAdapterController,
                                      IPedidoComandaIntegrationRestAdapterController pedidoComandaIntegrationRestAdapterController) {
        this.pedidoWorkFlowRestAdapterController = pedidoWorkFlowRestAdapterController;
        this.pedidoComandaIntegrationRestAdapterController = pedidoComandaIntegrationRestAdapterController;
    }

    @PutMapping("/{codigo}/prontificar")
    @ResponseBody
    public ResponseEntity<PedidoDTO> prontificarPedido(
            @ApiParam(value = "Código do Pedido") @PathVariable("codigo") String codigo) throws Exception {
        return ResponseEntity.ok().body(this.pedidoWorkFlowRestAdapterController.prontificarPedido(UUID.fromString(codigo)));
    }

    @PatchMapping("/{codigo}/enviar-comanda")
    @ResponseBody
    public ResponseEntity<PedidoDTO> enviarComanda(
            @ApiParam(value = "Código do Pedido") @PathVariable("codigo") UUID codigo) throws Exception {
        return ResponseEntity.ok().body(pedidoComandaIntegrationRestAdapterController.criaComanda(codigo));
    }
}
