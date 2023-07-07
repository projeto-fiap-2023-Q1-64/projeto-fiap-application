package br.fiap.projeto.contexto.pedido.infrastructure.integration;

import br.fiap.projeto.contexto.pedido.infrastructure.integration.port.Pagamento;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value="pedidoPagamentoIntegration", url = "http://localhost:8080/pagamento")
public interface PedidoPagamentoIntegration {
    @RequestMapping(method = RequestMethod.GET, value = "/busca-por-pedido/{codigoPedido}")
    public Pagamento buscaStatusPagamentoPorCodigoPedido(@PathVariable("codigoPedido") String codigoPedido);
}