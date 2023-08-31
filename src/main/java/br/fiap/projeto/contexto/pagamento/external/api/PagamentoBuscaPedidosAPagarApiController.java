package br.fiap.projeto.contexto.pagamento.external.api;

import br.fiap.projeto.contexto.pagamento.external.integration.IPedidoIntegration;
import br.fiap.projeto.contexto.pagamento.external.integration.port.Pedido;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value="/pagamento/pedido")
@Api(tags = {"Pagamentos"}, description = "Endpoints do domínio de Pagamentos")
public class PagamentoBuscaPedidosAPagarApiController {

    private final IPedidoIntegration pedidoIntegration;

    public PagamentoBuscaPedidosAPagarApiController(IPedidoIntegration pedidoIntegration) {
        this.pedidoIntegration = pedidoIntegration;
    }

    @GetMapping(value="a-pagar")
    @Transactional
    @ApiOperation(value = "Busca pedidos a pagar", notes="Esse endpoint recupera o(s) pedido(s) do domínio de Pedidos e que estão prontos para serem pagos.")
    public ResponseEntity <List<Pedido>> recuperaPedidosAPagar(){
        List<Pedido> pedidosIntegration = pedidoIntegration.buscaPedidosAPagar();
        return ResponseEntity.ok(pedidosIntegration);
    }
}
