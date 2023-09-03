package br.fiap.projeto.contexto.pagamento.external.api;

import br.fiap.projeto.contexto.pagamento.entity.Pagamento;
import br.fiap.projeto.contexto.pagamento.entity.integration.PagamentoPedido;
import br.fiap.projeto.contexto.pagamento.external.integration.IPedidoIntegration;
import br.fiap.projeto.contexto.pagamento.external.integration.port.Pedido;
import br.fiap.projeto.contexto.pagamento.usecase.port.repository.IPagamentoPedidoIntegrationGateway;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value="/pagamento/pedido")
@Api(tags = {"Pagamento - Integração Pedidos a Pagar"}, description = "Endpoint integrado ao domínio de Pedido para recuperar fila de pedidos a pagar.")
public class PagamentoBuscaPedidosAPagarApiController {

    private final IPagamentoPedidoIntegrationGateway pagamentoPedidoIntegrationGateway;

    public PagamentoBuscaPedidosAPagarApiController(IPagamentoPedidoIntegrationGateway pagamentoPedidoIntegrationGateway) {
        this.pagamentoPedidoIntegrationGateway = pagamentoPedidoIntegrationGateway;
    }

    @GetMapping(value="a-pagar")
    @Transactional
    @ApiOperation(value = "Busca pedidos a pagar", notes="Esse endpoint recupera o(s) pedido(s) do domínio de Pedidos e que estão prontos para serem pagos.")
    public ResponseEntity <List<PagamentoPedido>> recuperaPedidosAPagar(){
        List<PagamentoPedido> pedidosIntegration = pagamentoPedidoIntegrationGateway.buscaPedidosAPagar();
        return ResponseEntity.ok(pedidosIntegration);
    }
}
