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
     * #1 O domínio de Pagamento consome os códigos de pedidos que estão no status RECEBIDO
     * para gerar um código de pagamento
     *
     * Recupera os pedidos que foram iniciados e podem ser pagos
     *
     *TODO verificar o fluxo ao receber os pedidos do endpoint
     * externalizado pelo domínio de pedido
     *
     * @return
     */
    @GetMapping(value="/todos-a-pagar")
    @Transactional
    public ResponseEntity<List<Pedido>> listaPedidosAPagar(){
        List<Pedido> listaDePedidosAPagar = pedidoIntegration.buscaPedidosAPagar();
        if(listaDePedidosAPagar.size()==0){
            return ResponseEntity.noContent().build();
        }
        System.out.println("CONTROLLER: Um novo pedido está pronto para ser pago pagamento...");
        pagamentoServicePort.recebePedidosAPagar(new PedidoAPagarDTO(listaDePedidosAPagar));
        return ResponseEntity.ok().body(listaDePedidosAPagar);
    }

    /**
     * #2 Por meio do código do pedido persistido, é criado o Pagamento que será enviado ao Gateway
     *
     * Gera um pagamento para um pedido que é passado no corpo da requisição
     * TODO verificar o fluxo de exceção
     * @param pedidoAPagarDTO
     * @return
     */
    @PostMapping(value="/inicia-pagamento")
    @Transactional
    public ResponseEntity<PedidoAPagarDTO> iniciaPagamento(@RequestBody PedidoAPagarDTO pedidoAPagarDTO)  {
        pagamentoServicePort.criaPagamentoViaGateway(pedidoAPagarDTO);
        URI novoRecursoDePagamentoCriadoUri = ServletUriComponentsBuilder.fromCurrentRequest().buildAndExpand(pedidoAPagarDTO.getCodigoPedido()).toUri();
        return ResponseEntity.created(novoRecursoDePagamentoCriadoUri).body(pedidoAPagarDTO);
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

    /**
     * Retorna uma lista de Pagamentos que estão no status PENDING,
     * simulando que foram enviados oa Gateway de Pagamento
     * @return
     */
    @GetMapping(value="/todos-enviados-gateway")
    public ResponseEntity<List<PedidoAPagarDTO>> listaPedidos(){
        List<PedidoAPagarDTO> pedidosAPagar = pagamentoServicePort.buscaPedidosAPagar();
        return ResponseEntity.ok().body(pedidosAPagar);
    }

}
