package br.fiap.projeto.contexto.pedido.external.api;

import br.fiap.projeto.contexto.pedido.adapter.controller.port.IPedidoManagementRestAdapterController;
import br.fiap.projeto.contexto.pedido.adapter.controller.rest.response.PedidoDTO;
import io.swagger.annotations.ApiParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/pedidos")
public class PedidoManagementApiController {
    private final IPedidoManagementRestAdapterController pedidoManagementRestAdapterController;

    public PedidoManagementApiController(IPedidoManagementRestAdapterController pedidoManagementRestAdapterController) {
        this.pedidoManagementRestAdapterController = pedidoManagementRestAdapterController;
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<PedidoDTO> criaPedido(@ApiParam(value = "Código do Cliente (Opcional)") @RequestParam(value = "codigo_cliente", required = false) String codigoCliente) {
        return ResponseEntity.ok().body(this.pedidoManagementRestAdapterController.criaPedido(codigoCliente));
    }

    @PostMapping("/{codigo_pedido}/adicionar-produto/{codigo_produto}")
    @ResponseBody
    public ResponseEntity<PedidoDTO> adicionarProduto(@ApiParam(value = "Código do Pedido") @PathVariable("codigo_pedido") UUID codigoPedido,
                                                      @ApiParam(value = "Código do Produto") @PathVariable("codigo_produto") UUID codigoProduto) throws Exception {
        return ResponseEntity.ok().body(this.pedidoManagementRestAdapterController.adicionarProduto(codigoPedido, codigoProduto));
    }

    @DeleteMapping("/{codigo_pedido}/remover-produto/{produto_codigo}")
    public void removerProduto(@ApiParam(value = "Código do Pedido") @PathVariable("codigo_pedido") UUID codigoPedido,
                               @ApiParam(value = "Código do Produto") @PathVariable("produto_codigo") UUID produtoCodigo) throws Exception {
        this.pedidoManagementRestAdapterController.removerProduto(codigoPedido, produtoCodigo);
    }

    @PatchMapping("/{codigo_pedido}/aumentar-qtde-produto/{produto_codigo}")
    @ResponseBody
    public ResponseEntity<PedidoDTO> aumentarQuantidadeProduto(@ApiParam(value = "Código do Pedido") @PathVariable("codigo_pedido") UUID codigoPedido,
                                                               @ApiParam(value = "Código do Produto") @PathVariable("produto_codigo") UUID produtoCodigo) throws Exception {
        return ResponseEntity.ok().body(this.pedidoManagementRestAdapterController.aumentarQuantidade(codigoPedido, produtoCodigo));
    }

    @PatchMapping("/{codigo_pedido}/reduzir-qtde-produto/{produto_codigo}")
    @ResponseBody
    public ResponseEntity<PedidoDTO> reduzirQuantidadeProduto(@ApiParam(value = "Código do Pedido") @PathVariable("codigo_pedido") UUID codigoPedido,
                                                              @ApiParam(value = "Código do Produto") @PathVariable("produto_codigo") UUID produtoCodigo) throws Exception {
        return ResponseEntity.ok().body(this.pedidoManagementRestAdapterController.reduzirQuantidade(codigoPedido, produtoCodigo));
    }

}
