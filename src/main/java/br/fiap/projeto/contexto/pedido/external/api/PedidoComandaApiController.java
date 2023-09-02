package br.fiap.projeto.contexto.pedido.external.api;

import br.fiap.projeto.contexto.pedido.adapter.controller.port.IPedidoComandaIntegrationRestAdapterController;
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
@Api(tags = {"Pedido - Integração"}, description = "Endpoints de integrações do domínio de Pedidos")
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
    @ApiOperation(value = "Passa o pedido para o status de pronto", notes="Esse endpoint move o status para pronto")
    public ResponseEntity<PedidoDTO> prontificarPedido(
            @ApiParam(value = "Código do Pedido") @PathVariable("codigo") String codigo) throws Exception {
        return ResponseEntity.ok().body(this.pedidoWorkFlowRestAdapterController.prontificarPedido(UUID.fromString(codigo)));
    }

    @PatchMapping("/{codigo}/enviar-comanda")
    @ResponseBody
    @ApiOperation(value = "Enviar pedido para comanda", notes="Esse endpoint realiza uma integração com o contexto de comanda para criar um novo registro baseado no pedido enviado.")
    public ResponseEntity<PedidoDTO> enviarComanda(
            @ApiParam(value = "Código do Pedido") @PathVariable("codigo") UUID codigo) throws Exception {
        return ResponseEntity.ok().body(pedidoComandaIntegrationRestAdapterController.criaComanda(codigo));
    }
}
