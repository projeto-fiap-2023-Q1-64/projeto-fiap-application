package br.fiap.projeto.contexto.pedido.application.rest;

import br.fiap.projeto.contexto.pedido.application.rest.response.PedidoDTO;
import br.fiap.projeto.contexto.pedido.domain.port.service.PedidoService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/pedidos")
public class PedidoPagamentoController {
    private final PedidoService pedidoService;
    @Autowired
    public PedidoPagamentoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }
    //-------------------------------------------------------------------------//
    //                        INTEGRAÇÃO PAGAMENTO
    //-------------------------------------------------------------------------//
    @PatchMapping("/{codigo}/verificar-pagamento")
    @ResponseBody
    public PedidoDTO aprovarPedido(@ApiParam(value="Código do Pedido") @PathVariable("codigo") UUID codigo) throws Exception {
        return this.pedidoService.aprovar(codigo);
    }
}
