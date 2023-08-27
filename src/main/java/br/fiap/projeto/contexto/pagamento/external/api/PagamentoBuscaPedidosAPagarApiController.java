package br.fiap.projeto.contexto.pagamento.external.api;

import br.fiap.projeto.contexto.pagamento.external.integration.IPedidoIntegration;
import br.fiap.projeto.contexto.pagamento.external.integration.port.Pedido;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value="/pagamento/pedido")
public class PagamentoBuscaPedidosAPagarApiController {

    private final IPedidoIntegration pedidoIntegration;

    public PagamentoBuscaPedidosAPagarApiController(IPedidoIntegration pedidoIntegration) {
        this.pedidoIntegration = pedidoIntegration;
    }

    @GetMapping(value="a-pagar")
    @Transactional
    public ResponseEntity <List<Pedido>> recuperaPedidosAPagar(){
        List<Pedido> pedidosIntegration = pedidoIntegration.buscaPedidosAPagar();
        return ResponseEntity.ok(pedidosIntegration);
    }
}
