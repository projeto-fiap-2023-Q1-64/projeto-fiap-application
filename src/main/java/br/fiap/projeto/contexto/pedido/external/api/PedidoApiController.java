package br.fiap.projeto.contexto.pedido.external.api;

import br.fiap.projeto.contexto.pedido.adapter.controller.port.IPedidoManagementRestAdapterController;
import br.fiap.projeto.contexto.pedido.adapter.controller.rest.request.PedidoCriarDTO;
import br.fiap.projeto.contexto.pedido.adapter.controller.rest.request.ProdutoPedidoDTO;
import br.fiap.projeto.contexto.pedido.adapter.controller.rest.response.PedidoDTO;
import br.fiap.projeto.contexto.pedido.usecase.port.IPedidoManagementUseCase;
import br.fiap.projeto.contexto.pedido.external.integration.PedidoClienteIntegration;
import br.fiap.projeto.contexto.pedido.external.integration.PedidoProdutoIntegration;
import br.fiap.projeto.contexto.pedido.external.integration.port.Cliente;
import br.fiap.projeto.contexto.pedido.external.integration.port.Produto;
import io.swagger.annotations.ApiParam;
import org.hibernate.ObjectNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/pedidos")
public class PedidoApiController {
    private final IPedidoManagementRestAdapterController pedidoManagementRestAdapterController;
    private final PedidoProdutoIntegration pedidoProdutoIntegration;
    private final PedidoClienteIntegration pedidoClienteIntegration;

    public PedidoApiController(IPedidoManagementRestAdapterController pedidoManagementRestAdapterController, PedidoProdutoIntegration pedidoProdutoIntegration, PedidoClienteIntegration pedidoClienteIntegration) {
        this.pedidoManagementRestAdapterController = pedidoManagementRestAdapterController;
        this.pedidoProdutoIntegration = pedidoProdutoIntegration;
        this.pedidoClienteIntegration = pedidoClienteIntegration;
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<PedidoDTO> criaPedido(@ApiParam(value = "Código do Cliente (Opcional)") @RequestParam(value = "codigo_cliente", required = false) String codigoCliente) {
        PedidoCriarDTO pedidoCriarDTO = null;
        if (codigoCliente != null && !codigoCliente.isEmpty()) {
            Cliente cliente = pedidoClienteIntegration.busca(codigoCliente);
            if (cliente == null) {
                // TODO - Implementar tratamentos de Erro
                throw new ObjectNotFoundException(codigoCliente, "cliente");
            } else {
                pedidoCriarDTO = new PedidoCriarDTO(cliente);
            }
        }
        // TODO: Implementar tratamento do created para trabalhar com URI.
        return ResponseEntity.ok().body(this.pedidoManagementRestAdapterController.criaPedido(pedidoCriarDTO));
    }

    @PostMapping("/{codigo_pedido}/adicionar-produto/{codigo_produto}")
    @ResponseBody
    public ResponseEntity<PedidoDTO> adicionarProduto(@ApiParam(value = "Código do Pedido") @PathVariable("codigo_pedido") UUID codigoPedido,
                                                      @ApiParam(value = "Código do Produto") @PathVariable("codigo_produto") UUID codigoProduto) throws Exception {
        // TODO - Implementar tratamentos de Erro
        Produto produto = pedidoProdutoIntegration.getProduto(codigoProduto);
        ProdutoPedidoDTO produtoPedidoDTO = new ProdutoPedidoDTO(produto);
        return ResponseEntity.ok().body(this.pedidoManagementRestAdapterController.adicionarProduto(codigoPedido, produtoPedidoDTO));
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
