package br.fiap.projeto.contexto.pedido.external.api;

import br.fiap.projeto.contexto.pedido.adapter.controller.port.IPedidoManagementRestAdapterController;
import br.fiap.projeto.contexto.pedido.adapter.controller.rest.response.PedidoDTO;
import br.fiap.projeto.contexto.pedido.usecase.exception.InvalidStatusException;
import br.fiap.projeto.contexto.pedido.usecase.exception.NoItensException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/pedidos")
@Api(tags = { "Pedido - Operações" }, description = "Endpoints de operações gerais do domínio de Pedidos")
public class PedidoManagementApiController {
    private final IPedidoManagementRestAdapterController pedidoManagementRestAdapterController;

    public PedidoManagementApiController(IPedidoManagementRestAdapterController pedidoManagementRestAdapterController) {
        this.pedidoManagementRestAdapterController = pedidoManagementRestAdapterController;
    }

    @PostMapping
    @ResponseBody
    @ApiOperation(value = "Criação do pedido", notes = "Esse endpoint realiza a criação de um novo pedido podendo ou não ser enviado um cliente.")
    public ResponseEntity<PedidoDTO> criaPedido(
            @ApiParam(value = "Código do Cliente (Opcional)") @RequestParam(value = "codigo_cliente", required = false) String codigoCliente)
            throws InvalidStatusException, NoItensException {
        return ResponseEntity.ok().body(this.pedidoManagementRestAdapterController.criaPedido(codigoCliente));
    }

    @PostMapping("/{codigo_pedido}/adicionar-produto/{codigo_produto}")
    @ResponseBody
    @ApiOperation(value = "Adicionar produto", notes = "Esse endpoint adiciona a um pedido um novo produto")
    public ResponseEntity<PedidoDTO> adicionarProduto(
            @ApiParam(value = "Código do Pedido") @PathVariable("codigo_pedido") UUID codigoPedido,
            @ApiParam(value = "Código do Produto") @PathVariable("codigo_produto") UUID codigoProduto)
            throws Exception {
        return ResponseEntity.ok()
                .body(this.pedidoManagementRestAdapterController.adicionarProduto(codigoPedido, codigoProduto));
    }

    @DeleteMapping("/{codigo_pedido}/remover-produto/{produto_codigo}")
    @ApiOperation(value = "Remover produto", notes = "Esse endpoint remove um produto de um pedido ")
    public void removerProduto(@ApiParam(value = "Código do Pedido") @PathVariable("codigo_pedido") UUID codigoPedido,
            @ApiParam(value = "Código do Produto") @PathVariable("produto_codigo") UUID produtoCodigo)
            throws Exception {
        this.pedidoManagementRestAdapterController.removerProduto(codigoPedido, produtoCodigo);
    }

    @PatchMapping("/{codigo_pedido}/aumentar-qtde-produto/{produto_codigo}")
    @ResponseBody
    @ApiOperation(value = "Aumentar quantidade do produto", notes = "Esse endpoint adiciona uma unidade do produto enviado ao pedido definido.")
    public ResponseEntity<PedidoDTO> aumentarQuantidadeProduto(
            @ApiParam(value = "Código do Pedido") @PathVariable("codigo_pedido") UUID codigoPedido,
            @ApiParam(value = "Código do Produto") @PathVariable("produto_codigo") UUID produtoCodigo)
            throws Exception {
        return ResponseEntity.ok()
                .body(this.pedidoManagementRestAdapterController.aumentarQuantidade(codigoPedido, produtoCodigo));
    }

    @PatchMapping("/{codigo_pedido}/reduzir-qtde-produto/{produto_codigo}")
    @ResponseBody
    @ApiOperation(value = "Reduzir quantidade do produto", notes = "Esse endpoint reduz uma unidade do produto enviado ao pedido definido removendo o produto caso zerado.")
    public ResponseEntity<PedidoDTO> reduzirQuantidadeProduto(
            @ApiParam(value = "Código do Pedido") @PathVariable("codigo_pedido") UUID codigoPedido,
            @ApiParam(value = "Código do Produto") @PathVariable("produto_codigo") UUID produtoCodigo)
            throws Exception {
        return ResponseEntity.ok()
                .body(this.pedidoManagementRestAdapterController.reduzirQuantidade(codigoPedido, produtoCodigo));
    }
}