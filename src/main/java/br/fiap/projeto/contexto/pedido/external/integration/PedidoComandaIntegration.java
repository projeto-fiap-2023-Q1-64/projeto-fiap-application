package br.fiap.projeto.contexto.pedido.external.integration;

import br.fiap.projeto.contexto.pedido.external.integration.port.Comanda;
import br.fiap.projeto.contexto.pedido.external.integration.port.CriaComanda;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value="pedidoComandaIntegration", url = "http://localhost:${server.port}/comandas/")
public interface PedidoComandaIntegration {
    @RequestMapping(method = RequestMethod.POST, value = "")
    public Comanda criaComanda(@RequestBody CriaComanda criaComanda);
}
