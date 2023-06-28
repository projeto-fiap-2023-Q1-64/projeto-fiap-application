package br.fiap.projeto.contexto.pagamento.infrastructure.integration;

import br.fiap.projeto.contexto.pagamento.infrastructure.integration.port.Cliente;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(value = "clienteIntegration", url = "http://localhost:8080/clientes/")
public interface ClienteIntegration {

    @RequestMapping(method = RequestMethod.GET, value = "/todos")
    public List<Cliente> buscaTodos();
}
