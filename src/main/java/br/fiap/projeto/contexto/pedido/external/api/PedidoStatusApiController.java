package br.fiap.projeto.contexto.pedido.external.api;

import br.fiap.projeto.contexto.pedido.adapter.controller.rest.response.PedidoDTO;
import br.fiap.projeto.contexto.pedido.usecase.port.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoStatusApiController {
    private final PedidoService pedidoService;
    @Autowired
    public PedidoStatusApiController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    //-------------------------------------------------------------------------//
    //                        BUSCA POR STATUS
    //-------------------------------------------------------------------------//
    @GetMapping("busca-recebidos")
    @ResponseBody
    public ResponseEntity<List<PedidoDTO>> getPedidosRecebidos() {
        List<PedidoDTO> lista = this.pedidoService.buscarTodosRecebido();
        return ResponseEntity.ok().body(lista);
    }
    @GetMapping("busca-em-preparacao")
    @ResponseBody
    public ResponseEntity<List<PedidoDTO>> getPedidosEmPreparacao() {
        List<PedidoDTO> lista = this.pedidoService.buscarTodosEmPreparacao();
        return ResponseEntity.ok().body(lista);
    }
    @GetMapping("busca-pagos")
    @ResponseBody
    public ResponseEntity<List<PedidoDTO>> getPedidosPagos() {
        List<PedidoDTO> lista = this.pedidoService.buscarTodosPagos();
        return ResponseEntity.ok().body(lista);
    }
    @GetMapping("busca-prontos")
    @ResponseBody
    public ResponseEntity<List<PedidoDTO>> getProdutosProntos() {
        List<PedidoDTO> lista = this.pedidoService.buscarTodosPronto();
        return ResponseEntity.ok().body(lista);
    }
    @GetMapping("busca-entregues")
    @ResponseBody
    public ResponseEntity<List<PedidoDTO>> getProdutosEntregues() {
        List<PedidoDTO> lista = this.pedidoService.buscarTodosFinalizado();
        return ResponseEntity.ok().body(lista);
    }
}