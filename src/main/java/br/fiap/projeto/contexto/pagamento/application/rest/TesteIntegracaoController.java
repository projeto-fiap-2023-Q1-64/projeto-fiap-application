package br.fiap.projeto.contexto.pagamento.application.rest;

import br.fiap.projeto.contexto.pagamento.infrastructure.integration.ClienteIntegration;
import br.fiap.projeto.contexto.pagamento.infrastructure.integration.port.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/teste-integracao")
public class TesteIntegracaoController {

    @Autowired
    private ClienteIntegration clienteIntegration;

    @GetMapping("/clientes/todos")
    public List<Cliente> buscaTodos() {

        return clienteIntegration.buscaTodos();
    }

}
