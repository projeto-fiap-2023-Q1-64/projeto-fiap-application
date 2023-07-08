package br.fiap.projeto.contexto.comanda.infrastructure.integration;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.fiap.projeto.contexto.comanda.infrastructure.integration.port.PedidoDTO;

@FeignClient(value = "comandaPedidoIntegration", url = "http://localhost:8080/pedidos")
public interface ComandaPedidoIntegration {
    @ResponseBody
    @RequestMapping(method = RequestMethod.PATCH, value = "/{codigo}/prontificar")
    public PedidoDTO prontificar(@PathVariable("codigo") String codigoPedido);
}
