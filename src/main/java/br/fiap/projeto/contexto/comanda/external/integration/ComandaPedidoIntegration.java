package br.fiap.projeto.contexto.comanda.external.integration;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.UUID;

@FeignClient(value = "comandaPedidoIntegration", url = "http://localhost:8080/pedidos")
public interface ComandaPedidoIntegration {
    // @ResponseBody
    @RequestMapping(method = RequestMethod.PUT, value = "/{codigo}/prontificar")
    public ResponseEntity<UUID> prontificar(@PathVariable("codigo") String codigoPedido);
}
