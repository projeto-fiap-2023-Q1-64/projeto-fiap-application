package br.fiap.projeto.contexto.pedido.external.integration;

import br.fiap.projeto.contexto.pedido.external.integration.port.Cliente;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value="pedidoClienteIntegration", url = "http://localhost:8080/")
public interface PedidoClienteIntegration {
    @RequestMapping(method = RequestMethod.GET, value = "clientes/{codigo}")
    public Cliente busca(@PathVariable("codigo") String codigo);
}