package br.fiap.projeto.contexto.pedido.external.api;

import br.fiap.projeto.contexto.pedido.adapter.controller.port.IPedidoQueryRestAdapterController;
import br.fiap.projeto.contexto.pedido.adapter.controller.rest.response.PedidoDTO;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/pedidos")
public class PedidoQueryApiController {
    private final IPedidoQueryRestAdapterController pedidoQueryRestAdapterController;

    @Autowired
    public PedidoQueryApiController(IPedidoQueryRestAdapterController pedidoQueryRestAdapterController) {
        this.pedidoQueryRestAdapterController = pedidoQueryRestAdapterController;
    }
    @GetMapping("/{codigo}")
    @ResponseBody
    public ResponseEntity<PedidoDTO> getPedido(@ApiParam(value = "Código do Pedido") @PathVariable("codigo") UUID codigo) {
        return ResponseEntity.ok().body(this.pedidoQueryRestAdapterController.buscaPedido(codigo));
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
    public ResponseEntity<List<PedidoDTO>> getPedidosProntos() {
        List<PedidoDTO> lista = this.pedidoQueryRestAdapterController.buscarTodosPronto();
        return ResponseEntity.ok().body(lista);
    }

    @GetMapping("busca-entregues")
    @ResponseBody
    public ResponseEntity<List<PedidoDTO>> getPedidosEntregues() {
        List<PedidoDTO> lista = this.pedidoQueryRestAdapterController.buscarTodosFinalizado();
        return ResponseEntity.ok().body(lista);
    }
    @GetMapping("busca-cancelados")
    @ResponseBody
    public ResponseEntity<List<PedidoDTO>> getPedidosCancelados() {
        List<PedidoDTO> lista = this.pedidoQueryRestAdapterController.buscarTodosCancelado();
        return ResponseEntity.ok().body(lista);
    }

    @GetMapping("busca-pedidos")
    @ResponseBody
    public ResponseEntity<List<PedidoDTO>> getPedidos() {
        List<PedidoDTO> lista = this.pedidoQueryRestAdapterController.buscarPorStatusEData();
        return ResponseEntity.ok().body(lista);
    }
}