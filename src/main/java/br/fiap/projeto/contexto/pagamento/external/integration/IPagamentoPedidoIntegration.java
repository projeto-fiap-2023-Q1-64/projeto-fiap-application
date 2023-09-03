package br.fiap.projeto.contexto.pagamento.external.integration;

import br.fiap.projeto.contexto.pagamento.entity.integration.PagamentoPedidoResponse;
import br.fiap.projeto.contexto.pagamento.external.integration.port.Pedido;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value="pagamentoPedidoIntegration", url = "http://localhost:8080/pedidos")
public interface IPagamentoPedidoIntegration {

    @RequestMapping(method = RequestMethod.PATCH, value="/recebe-retorno-pagamento")
    public Pedido atualizaStatusPagamentoPedido(@RequestBody PagamentoPedidoResponse pagamentoPedidoResponse);

}

