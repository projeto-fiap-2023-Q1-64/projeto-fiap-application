package br.fiap.projeto.contexto.comanda.external.integration;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "comandaPedidoIntegration", url = "http://localhost:${server.port}/pedidos")
public interface ComandaPedidoIntegration {
    // @ResponseBody
    @RequestMapping(method = RequestMethod.PUT, value = "/{codigo}/prontificar")
    public ResponseEntity<String> prontificar(@PathVariable("codigo") String codigoPedido);
}
