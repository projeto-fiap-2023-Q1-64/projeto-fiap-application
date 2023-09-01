package br.fiap.projeto.contexto.pagamento.external.integration;

import br.fiap.projeto.contexto.pagamento.external.integration.port.Pedido;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;


@FeignClient(value="pedidoIntegration", url = "http://localhost:${server.port}/pedidos")
public interface IPedidoIntegration {

    @RequestMapping(method = RequestMethod.GET, value = "/busca-recebidos")
    public List<Pedido> buscaPedidosAPagar();
}
