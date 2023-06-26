package br.fiap.projeto.contexto.pedido.application.rest;

import br.fiap.projeto.contexto.pedido.domain.dto.PedidoDTO;
import br.fiap.projeto.contexto.pedido.domain.port.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
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
    @GetMapping
    @ResponseBody
    List<PedidoDTO> getPedidos() {
        return this.pedidoService.buscaTodos();
    }
    @GetMapping("/{codigo}")
    @ResponseBody
    PedidoDTO getPedidos(@PathVariable("codigo") UUID codigo) {
        return this.pedidoService.buscaPedido(codigo);
    }
    @PostMapping
    @ResponseBody
    PedidoDTO criaPedido(@RequestBody PedidoDTO pedido) {
        return this.pedidoService.criaPedido(pedido);
    }
    @PutMapping("/{codigo}")
    PedidoDTO atualizaPedido(@PathVariable UUID codigo,
                             @RequestBody PedidoDTO pedido){
        return this.pedidoService.atualizaPedido(codigo, pedido);
    }
    @DeleteMapping("/{codigo}")
    void removerPedido(@PathVariable("codigo") UUID codigo){
        this.pedidoService.removePedido(codigo);
    }
}