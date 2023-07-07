package br.fiap.projeto.contexto.comanda.infrastructure.integration;

import java.util.UUID;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import br.fiap.projeto.contexto.pedido.domain.Pedido;

@FeignClient(value = "comandaPedidoIntegration", url = "http://localhost:8080/")
public interface ComandaPedidoIntegration {
    @PatchMapping("/{codigo}/prontificar")

    @ResponseBody
    public Pedido prontificar(@PathVariable("codigo") UUID codigoPedido);
}
