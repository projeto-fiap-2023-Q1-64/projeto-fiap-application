package br.fiap.projeto.contexto.pedido.external.integration;

import br.fiap.projeto.contexto.pedido.external.integration.port.Pagamento;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value="pedidoPagamentoIntegration", url = "http://localhost:${server.port}/pagamento")
public interface PedidoPagamentoIntegration {
    @RequestMapping(method = RequestMethod.GET, value = "/busca/por-codigo-pedido/{codigoPedido}")
    public Pagamento buscaStatusPagamentoPorCodigoPedido(@PathVariable("codigoPedido") String codigoPedido);
}