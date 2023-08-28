package br.fiap.projeto.contexto.pagamento.application.rest;

import br.fiap.projeto.contexto.pagamento.application.rest.response.PedidoAPagarDTO;
import br.fiap.projeto.contexto.pagamento.domain.port.service.PagamentoServicePort;
import br.fiap.projeto.contexto.pagamento.infrastructure.integration.PedidoIntegration;
import br.fiap.projeto.contexto.pagamento.infrastructure.integration.port.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

/**
 * Endpoint que simula o processamento do pagamento de pedidos recebidos
 * e faz o intermédio com o gateway de pagamentos
 */
@RestController
@RequestMapping("/pagamento/gateway")
public class PagamentosAProcessarController {

    private final PagamentoServicePort pagamentoServicePort;
    private final PedidoIntegration pedidoIntegration;

    @Autowired
    public PagamentosAProcessarController(PagamentoServicePort pagamentoServicePort, PedidoIntegration pedidoIntegration) {
        this.pagamentoServicePort = pagamentoServicePort;
        this.pedidoIntegration = pedidoIntegration;
    }

    /**
     * #3 Após ter criado o Pagamento, é despachado/redirecionado ao gateway para
     * que seja processado pelo Sistema Externo
     *
     * WIP: A intenção é manter somente este endpoint, ao enviar para o Gateway já será criado um código de pagamento
     * substituindo a etapa 2.
     *
     * Nota: Verificar se é interessante manter esse endpoint com as responsabilidades de:
     * enviar ao gateway e criar o código de pagamento que será utilizado no processament interno deste domínio
     *
     * @param pedidoAPagarDTO
     * @return
     */
    @PostMapping(value="/url-do-gateway")
    @Transactional
    public ResponseEntity<Void> enviaCompraParaGateway(@RequestBody PedidoAPagarDTO pedidoAPagarDTO) {
        pagamentoServicePort.enviaGatewayDePagamento(pedidoAPagarDTO);
        return ResponseEntity.ok().build();
    }

}
