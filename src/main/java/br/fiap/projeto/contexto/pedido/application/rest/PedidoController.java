package br.fiap.projeto.contexto.pedido.application.rest;

import br.fiap.projeto.contexto.pedido.domain.dto.PedidoCriarDTO;
import br.fiap.projeto.contexto.pedido.domain.dto.PedidoDTO;
import br.fiap.projeto.contexto.pedido.domain.dto.ProdutoPedidoDTO;
import br.fiap.projeto.contexto.pedido.domain.port.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
    private final PedidoService pedidoService;
    @Autowired
    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
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
    @PostMapping("/{codigo}/adicionar-produto")
    @ResponseBody
    public PedidoDTO adicionarProduto(@PathVariable("codigo") UUID codigoPedido, @RequestBody ProdutoPedidoDTO produtoPedidoDTO) throws Exception {
        return this.pedidoService.adicionarProduto(codigoPedido,produtoPedidoDTO);
    }
    @PostMapping("/{codigo}/remover-produto/{produto_codigo}")
    public void removerProduto(@PathVariable("codigo") UUID codigoPedido, @PathVariable("produto_codigo") UUID produtCodigo) throws Exception {
        this.pedidoService.removerProduto(codigoPedido,produtCodigo);
    }
    @PostMapping("/{codigo}/aumentar-qtde-produto")
    @ResponseBody
    public PedidoDTO adicionarQuantidadeProduto(@PathVariable("codigo") UUID codigoPedido, @RequestBody ProdutoPedidoDTO produtoPedidoDTO) throws Exception {
        return this.pedidoService.aumentarQuantidade(codigoPedido,produtoPedidoDTO);
    }
    @PostMapping("/{codigo}/reduzir-qtde-produto")
    @ResponseBody
    public PedidoDTO reduzirQuantidadeProduto(@PathVariable("codigo") UUID codigoPedido, @RequestBody ProdutoPedidoDTO produtoPedidoDTO) throws Exception {
        return this.pedidoService.reduzirQuantidade(codigoPedido,produtoPedidoDTO);
    }
}