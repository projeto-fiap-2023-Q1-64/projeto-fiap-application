package br.fiap.projeto.contexto.pedido.external.api;

import br.fiap.projeto.contexto.pedido.adapter.controller.port.IPedidoQueryRestAdapterController;
import br.fiap.projeto.contexto.pedido.adapter.controller.rest.response.PedidoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoQueryApiController {
    private final IPedidoQueryRestAdapterController pedidoQueryRestAdapterController;

    @Autowired
    public PedidoQueryApiController(IPedidoQueryRestAdapterController pedidoQueryRestAdapterController) {
        this.pedidoQueryRestAdapterController = pedidoQueryRestAdapterController;
    }

    @GetMapping("busca-recebidos")
    @ResponseBody
    public ResponseEntity<List<PedidoDTO>> getPedidosRecebidos() {
        List<PedidoDTO> lista = this.pedidoQueryRestAdapterController.buscarTodosRecebido();
        return ResponseEntity.ok().body(lista);
    }

    @GetMapping("busca-em-preparacao")
    @ResponseBody
    public ResponseEntity<List<PedidoDTO>> getPedidosEmPreparacao() {
        List<PedidoDTO> lista = this.pedidoQueryRestAdapterController.buscarTodosEmPreparacao();
        return ResponseEntity.ok().body(lista);
    }

    @GetMapping("busca-pagos")
    @ResponseBody
    public ResponseEntity<List<PedidoDTO>> getPedidosPagos() {
        List<PedidoDTO> lista = this.pedidoQueryRestAdapterController.buscarTodosPagos();
        return ResponseEntity.ok().body(lista);
    }

    @GetMapping("busca-prontos")
    @ResponseBody
    public ResponseEntity<List<PedidoDTO>> getProdutosProntos() {
        List<PedidoDTO> lista = this.pedidoQueryRestAdapterController.buscarTodosPronto();
        return ResponseEntity.ok().body(lista);
    }

    @GetMapping("busca-entregues")
    @ResponseBody
    public ResponseEntity<List<PedidoDTO>> getProdutosEntregues() {
        List<PedidoDTO> lista = this.pedidoQueryRestAdapterController.buscarTodosFinalizado();
        return ResponseEntity.ok().body(lista);
    }
}