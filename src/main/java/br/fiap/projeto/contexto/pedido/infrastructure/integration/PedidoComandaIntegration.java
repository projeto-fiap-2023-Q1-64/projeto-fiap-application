package br.fiap.projeto.contexto.pedido.infrastructure.integration;

import br.fiap.projeto.contexto.pedido.infrastructure.integration.port.Comanda;
import br.fiap.projeto.contexto.pedido.infrastructure.integration.port.CriaComanda;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.UUID;

@FeignClient(value="pedidoComandaIntegration", url = "http://localhost:8080/comandas/")
public interface PedidoComandaIntegration {
    @RequestMapping(method = RequestMethod.POST, value = "")
    public Comanda criaComanda(@RequestBody CriaComanda criaComanda);
}
