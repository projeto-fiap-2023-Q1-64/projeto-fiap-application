package br.fiap.projeto.contexto.pedido.infrastructure.integration;

import br.fiap.projeto.contexto.pedido.infrastructure.integration.port.Produto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.UUID;

@FeignClient(value="produtoIntegration", url = "http://localhost:8080/")
public interface ProdutoIntegration {
    @RequestMapping(method = RequestMethod.GET, value = "produtos/{codigo}")
    public Produto getProduto(@PathVariable("codigo") UUID codigo);
}
