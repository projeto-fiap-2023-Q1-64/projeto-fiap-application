package br.fiap.projeto.contexto.pedido.application.rest;

import br.fiap.projeto.contexto.pedido.application.rest.request.PedidoCriarDTO;
import br.fiap.projeto.contexto.pedido.application.rest.request.ProdutoPedidoDTO;
import br.fiap.projeto.contexto.pedido.application.rest.response.PedidoDTO;
import br.fiap.projeto.contexto.pedido.domain.port.service.PedidoService;
import br.fiap.projeto.contexto.pedido.infrastructure.integration.ProdutoIntegration;
import br.fiap.projeto.contexto.pedido.infrastructure.integration.port.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
    private final PedidoService pedidoService;
    private final ProdutoIntegration produtoIntegration;
    @Autowired
    public PedidoController(PedidoService pedidoService, ProdutoIntegration produtoIntegration) {
        this.pedidoService = pedidoService;
        this.produtoIntegration = produtoIntegration;
    }
    //-------------------------------------------------------------------------//
    //                         BASE CRUD
    //-------------------------------------------------------------------------//
    @PostMapping
    @ResponseBody
    public PedidoDTO criaPedido(@RequestBody PedidoCriarDTO pedido) {
        return this.pedidoService.criaPedido(pedido);
    }
    //-------------------------------------------------------------------------//
    //                        BUSCA POR STATUS
    //-------------------------------------------------------------------------//
    @GetMapping("busca-recebidos")
    @ResponseBody
    public ResponseEntity<List<PedidoDTO>> getProdutosRecebidos() {
        List<PedidoDTO> lista = this.pedidoService.buscarTodosRecebido();
        return ResponseEntity.ok().body(lista);
    }
    @GetMapping("busca-em-preparacao")
    @ResponseBody
    public ResponseEntity<List<PedidoDTO>> getProdutosEmPreparacao() {
        List<PedidoDTO> lista = this.pedidoService.buscarTodosEmPreparacao();
        return ResponseEntity.ok().body(lista);
    }
    @GetMapping("busca-prontos")
    @ResponseBody
    public ResponseEntity<List<PedidoDTO>> getProdutosProntos() {
        List<PedidoDTO> lista = this.pedidoService.buscarTodosPronto();
        return ResponseEntity.ok().body(lista);
    }
    @GetMapping("busca-finalizados")
    @ResponseBody
    public ResponseEntity<List<PedidoDTO>> getProdutosFinalizados() {
        List<PedidoDTO> lista = this.pedidoService.buscarTodosFinalizado();
        return ResponseEntity.ok().body(lista);
    }
    //-------------------------------------------------------------------------//
    //                        ATUALIZA STATUS
    //-------------------------------------------------------------------------//
    @PatchMapping("/{codigo}/receber")
    @ResponseBody
    public PedidoDTO receberPedido(@PathVariable("codigo") UUID codigo) throws Exception {
        return this.pedidoService.receber(codigo);
    }
    @PatchMapping("/{codigo}/aprovar")
    @ResponseBody
    public PedidoDTO aprovarPedido(@PathVariable("codigo") UUID codigo) throws Exception {
        return this.pedidoService.aprovar(codigo);
    }
    @PatchMapping("/{codigo}/prontificar")
    @ResponseBody
    public PedidoDTO prontificarPedido(@PathVariable("codigo") UUID codigo) throws Exception {
        return this.pedidoService.prontificar(codigo);
    }
    @PatchMapping("/{codigo}/finalizar")
    @ResponseBody
    public PedidoDTO finalizarPedido(@PathVariable("codigo") UUID codigo) throws Exception {
        return this.pedidoService.finalizar(codigo);
    }
    //-------------------------------------------------------------------------//
    //                MÉTODOS DE MANUPULAÇÃO DE ITENS DO PEDIDO
    //-------------------------------------------------------------------------//
//    @PostMapping("/{codigo_pedido}/adicionar-produto")
//    @ResponseBody
//    public PedidoDTO adicionarProduto(@PathVariable("codigo_pedido") UUID codigoPedido, @RequestBody ProdutoPedidoDTO produtoPedidoDTO) throws Exception {
//        return this.pedidoService.adicionarProduto(codigoPedido,produtoPedidoDTO);
//    }
    @PostMapping("/{codigo_pedido}/adicionar-produto/{codigo_produto}")
    @ResponseBody
    public ResponseEntity<PedidoDTO> adicionarProduto(@PathVariable("codigo_pedido") UUID codigoPedido,
                                      @PathVariable("codigo_produto") UUID codigoProduto) throws Exception {
        // TODO - Implementar tratamentos de Erro
        Produto produto = produtoIntegration.getProduto(codigoProduto);
        ProdutoPedidoDTO produtoPedidoDTO = new ProdutoPedidoDTO(produto);
        return ResponseEntity.ok().body(this.pedidoService.adicionarProduto(codigoPedido, produtoPedidoDTO));
    }
    @DeleteMapping("/{codigo_pedido}/remover-produto/{produto_codigo}")
    public void removerProduto(@PathVariable("codigo_pedido") UUID codigoPedido, @PathVariable("produto_codigo") UUID produtoCodigo) throws Exception {
        this.pedidoService.removerProduto(codigoPedido,produtoCodigo);
    }
    @PatchMapping("/{codigo_pedido}/aumentar-qtde-produto/{produto_codigo}")
    @ResponseBody
    public PedidoDTO adicionarQuantidadeProduto(@PathVariable("codigo_pedido") UUID codigoPedido, @PathVariable("produto_codigo") UUID produtoCodigo) throws Exception {
        return this.pedidoService.aumentarQuantidade(codigoPedido,produtoCodigo);
    }
    @PatchMapping("/{codigo_pedido}/reduzir-qtde-produto/{produto_codigo}")
    @ResponseBody
    public PedidoDTO reduzirQuantidadeProduto(@PathVariable("codigo_pedido") UUID codigoPedido, @PathVariable("produto_codigo") UUID produtoCodigo) throws Exception {
        return this.pedidoService.reduzirQuantidade(codigoPedido,produtoCodigo);
    }
}