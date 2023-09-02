package br.fiap.projeto.contexto.pedido.external.api;

import br.fiap.projeto.contexto.pedido.adapter.controller.port.IPedidoPagamentoIntegrationRestAdapterController;
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
public class PedidoPagamentoApiController {
    private final IPedidoPagamentoIntegrationRestAdapterController pedidoPagamentoIntegrationRestAdapterController;

    @Autowired
    public PedidoPagamentoApiController(IPedidoPagamentoIntegrationRestAdapterController pedidoPagamentoIntegrationRestAdapterController) {
        this.pedidoPagamentoIntegrationRestAdapterController = pedidoPagamentoIntegrationRestAdapterController;
    }


    @PatchMapping("/{codigo}/atualizar-pagamento")
    @ResponseBody
    @ApiOperation(value = "Atualizar Pagamento", notes="Esse endpoint realiza uma consulta para verificar o status de pagamento e com o resultado cancelar ou definir o pedido como pago.")
    public ResponseEntity<?> atualizarPagamento(@ApiParam(value = "Código do Pedido") @PathVariable("codigo") UUID codigo) throws Exception {
        return ResponseEntity.ok().body(pedidoPagamentoIntegrationRestAdapterController.atualizarPagamentoPedido(codigo));
    }
}