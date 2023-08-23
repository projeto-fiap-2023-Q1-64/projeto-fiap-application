package br.fiap.projeto.contexto.pedido.external.api;

import br.fiap.projeto.contexto.pedido.adapter.controller.port.IPedidoWorkFlowRestAdapterController;
import br.fiap.projeto.contexto.pedido.adapter.controller.rest.response.PedidoDTO;
import br.fiap.projeto.contexto.pedido.entity.enums.StatusPedido;
import br.fiap.projeto.contexto.pedido.external.integration.PedidoComandaIntegration;
import br.fiap.projeto.contexto.pedido.external.integration.port.Comanda;
import br.fiap.projeto.contexto.pedido.external.integration.port.CriaComanda;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/pedidos")
public class PedidoComandaApiController {
    private final IPedidoWorkFlowRestAdapterController pedidoWorkFlowRestAdapterController;
    private final PedidoComandaIntegration pedidoComandaIntegration;

    @Autowired
    public PedidoComandaApiController(IPedidoWorkFlowRestAdapterController pedidoWorkFlowRestAdapterController,
                                      PedidoComandaIntegration pedidoComandaIntegration) {
        this.pedidoWorkFlowRestAdapterController = pedidoWorkFlowRestAdapterController;
        this.pedidoComandaIntegration = pedidoComandaIntegration;
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
        PedidoDTO pedidoDTO = null;
        try {
            pedidoDTO = this.pedidoWorkFlowRestAdapterController.prepararPedido(codigo);
            if (pedidoDTO == null || !pedidoDTO.getStatus().equals(StatusPedido.EM_PREPARACAO)) {
                System.out.println("Erro na atualização do status!");
                throw new Exception("Erro na atualização do status!");
            }
            Comanda comanda = pedidoComandaIntegration.criaComanda(new CriaComanda(codigo));
            // Verifica se criou comanda
            if (comanda == null || comanda.getCodigoComanda().toString().isEmpty()) {
                System.out.println("Erro na criação da comanda!");
                throw new Exception("Erro na criação da comanda!");
            }
        } catch (Exception e) {
            System.out.println("Erro na integração com a Comanda!");
            throw new Exception("Erro na integração com a Comanda!");
        }
        return ResponseEntity.ok().body(pedidoDTO);
    }
}
