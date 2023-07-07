package br.fiap.projeto.contexto.pedido.application.rest;

import br.fiap.projeto.contexto.pedido.application.rest.response.PedidoDTO;
import br.fiap.projeto.contexto.pedido.domain.port.service.PedidoService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/pedidos")
public class PedidoComandaController {
    private final PedidoService pedidoService;
    @Autowired
    public PedidoComandaController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }
    //-------------------------------------------------------------------------//
    //                        INTEGRAÇÃO COMANDA
    //-------------------------------------------------------------------------//
    @PatchMapping("/{codigo}/prontificar")
    @ResponseBody
    public PedidoDTO prontificarPedido(@ApiParam(value="Código do Pedido") @PathVariable("codigo") UUID codigo) throws Exception {
        return this.pedidoService.prontificar(codigo);
    }
}
