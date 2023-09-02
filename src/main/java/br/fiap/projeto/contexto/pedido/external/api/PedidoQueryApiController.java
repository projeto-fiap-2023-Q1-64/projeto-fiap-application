package br.fiap.projeto.contexto.pedido.external.api;

import br.fiap.projeto.contexto.pedido.adapter.controller.port.IPedidoQueryRestAdapterController;
import br.fiap.projeto.contexto.pedido.adapter.controller.rest.response.PedidoDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/pedidos")
@Api(tags = {"Pedido - Buscas"}, description = "Endpoints de buscas do domínio de Pedidos")
public class PedidoQueryApiController {
    private final IPedidoQueryRestAdapterController pedidoQueryRestAdapterController;

    @Autowired
    public PedidoQueryApiController(IPedidoQueryRestAdapterController pedidoQueryRestAdapterController) {
        this.pedidoQueryRestAdapterController = pedidoQueryRestAdapterController;
    }
    @GetMapping("/{codigo}")
    @ResponseBody
    @ApiOperation(value = "Buscar Pedido", notes="Esse endpoint recupera as informações de um pedido.")
    public ResponseEntity<PedidoDTO> getPedido(@ApiParam(value = "Código do Pedido") @PathVariable("codigo") UUID codigo) {
        return ResponseEntity.ok().body(this.pedidoQueryRestAdapterController.buscaPedido(codigo));
    }

    @GetMapping("busca-recebidos")
    @ResponseBody
    @ApiOperation(value = "Buscar Pedidos Recebidos", notes="Esse endpoint recupera uma lista com todos os produtos com status Recebido.")
    public ResponseEntity<List<PedidoDTO>> getPedidosRecebidos() {
        List<PedidoDTO> lista = this.pedidoQueryRestAdapterController.buscarTodosRecebido();
        return ResponseEntity.ok().body(lista);
    }

    @GetMapping("busca-em-preparacao")
    @ResponseBody
    @ApiOperation(value = "Buscar Pedidos Preparação", notes="Esse endpoint recupera uma lista com todos os produtos com status Preparação.")
    public ResponseEntity<List<PedidoDTO>> getPedidosEmPreparacao() {
        List<PedidoDTO> lista = this.pedidoQueryRestAdapterController.buscarTodosEmPreparacao();
        return ResponseEntity.ok().body(lista);
    }

    @GetMapping("busca-pagos")
    @ResponseBody
    @ApiOperation(value = "Buscar Pedidos Pagos", notes="Esse endpoint recupera uma lista com todos os produtos com status Pago.")
    public ResponseEntity<List<PedidoDTO>> getPedidosPagos() {
        List<PedidoDTO> lista = this.pedidoQueryRestAdapterController.buscarTodosPagos();
        return ResponseEntity.ok().body(lista);
    }

    @GetMapping("busca-prontos")
    @ResponseBody
    @ApiOperation(value = "Buscar Pedidos Prontos", notes="Esse endpoint recupera uma lista com todos os produtos com status Pronto.")
    public ResponseEntity<List<PedidoDTO>> getPedidosProntos() {
        List<PedidoDTO> lista = this.pedidoQueryRestAdapterController.buscarTodosPronto();
        return ResponseEntity.ok().body(lista);
    }

    @GetMapping("busca-entregues")
    @ResponseBody
    @ApiOperation(value = "Buscar Pedidos Entregues", notes="Esse endpoint recupera uma lista com todos os produtos com status Entregue.")
    public ResponseEntity<List<PedidoDTO>> getPedidosEntregues() {
        List<PedidoDTO> lista = this.pedidoQueryRestAdapterController.buscarTodosFinalizado();
        return ResponseEntity.ok().body(lista);
    }
    @GetMapping("busca-cancelados")
    @ResponseBody
    @ApiOperation(value = "Buscar Pedidos Cancelados", notes="Esse endpoint recupera uma lista com todos os produtos com status Cancelado.")
    public ResponseEntity<List<PedidoDTO>> getPedidosCancelados() {
        List<PedidoDTO> lista = this.pedidoQueryRestAdapterController.buscarTodosCancelado();
        return ResponseEntity.ok().body(lista);
    }

    @GetMapping("busca-pedidos")
    @ResponseBody
    @ApiOperation(value = "Buscar Pedidos em Aberto e Ordenados", notes="Esse endpoint recupera uma lista com todos os produtos em aberto ordernado por status e data de criação.")
    public ResponseEntity<List<PedidoDTO>> getPedidos() {
        List<PedidoDTO> lista = this.pedidoQueryRestAdapterController.buscarPorStatusEData();
        return ResponseEntity.ok().body(lista);
    }
}