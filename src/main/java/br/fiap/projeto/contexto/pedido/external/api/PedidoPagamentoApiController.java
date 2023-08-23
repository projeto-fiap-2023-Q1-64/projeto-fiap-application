package br.fiap.projeto.contexto.pedido.external.api;

import br.fiap.projeto.contexto.pedido.adapter.controller.port.IPedidoWorkFlowRestAdapterController;
import br.fiap.projeto.contexto.pedido.adapter.controller.rest.response.PedidoDTO;
import br.fiap.projeto.contexto.pedido.entity.enums.StatusPagamento;
import br.fiap.projeto.contexto.pedido.external.integration.PedidoPagamentoIntegration;
import br.fiap.projeto.contexto.pedido.external.integration.port.Pagamento;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/pedidos")
public class PedidoPagamentoApiController {
    private final IPedidoWorkFlowRestAdapterController pedidoWorkFlowRestAdapterController;
    private final PedidoPagamentoIntegration pedidoPagamentoIntegration;

    @Autowired
    public PedidoPagamentoApiController(IPedidoWorkFlowRestAdapterController pedidoWorkFlowRestAdapterController, PedidoPagamentoIntegration pedidoPagamentoIntegration) {
        this.pedidoWorkFlowRestAdapterController = pedidoWorkFlowRestAdapterController;
        this.pedidoPagamentoIntegration = pedidoPagamentoIntegration;
    }

    @PatchMapping("/{codigo}/verificar-pagamento")
    @ResponseBody
    public ResponseEntity<PedidoDTO> verificarPagamento(@ApiParam(value = "Código do Pedido") @PathVariable("codigo") UUID codigo) throws Exception {
        PedidoDTO pedidoDTO = null;
        try {
            Pagamento pagamento = pedidoPagamentoIntegration.buscaStatusPagamentoPorCodigoPedido(codigo.toString());
            if (pagamento != null && pagamento.getStatusPagamento().getDescricao().equals(StatusPagamento.APPROVED.getDescricao())) {
                pedidoDTO = this.pedidoWorkFlowRestAdapterController.pagarPedido(codigo);
            } else {
                System.out.println("Pedido não encontrado ou não aprovado!");
                throw new Exception("Pedido não encontrado ou não aprovado!");
            }
        } catch (Exception e) {
            System.out.println("Erro ao realizar integração!");
            throw new Exception("Erro ao realizar integração!", e);
        }
        return ResponseEntity.ok().body(pedidoDTO);
    }
}