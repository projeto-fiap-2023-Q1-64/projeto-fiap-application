package br.fiap.projeto.contexto.pagamento.application.rest;

import br.fiap.projeto.contexto.pagamento.application.rest.response.PagamentoDTO;
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

@RestController
@RequestMapping("/pagamento/gateway")
public class PedidosAPagarController {

    private final PagamentoServicePort pagamentoServicePort;
    private final PedidoIntegration pedidoIntegration;

    @Autowired
    public PedidosAPagarController(PagamentoServicePort pagamentoServicePort, PedidoIntegration pedidoIntegration) {
        this.pagamentoServicePort = pagamentoServicePort;
        this.pedidoIntegration = pedidoIntegration;
    }

    /**
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
     * Recupera os pedidos que foram iniciados e podem ser pagos
     *
     * TODO verificar o fluxo ao receber os pedidos do endpoint
     * externalizado pelo domínio de pedido
     *
     * @return
     */
    @GetMapping(value="/todos-a-pagar")
    @Transactional
    public ResponseEntity<List<Pedido>> listaPedidosAPagar(){
        List<Pedido> listaDePedidosAPagar = pedidoIntegration.buscaPedidosAPagar();
        pagamentoServicePort.recebePedidosAPagar(new PedidoAPagarDTO(listaDePedidosAPagar));
        return ResponseEntity.ok().body(listaDePedidosAPagar);
    }

    /**
     * Envia ao Gateway de pagamento o pedido recebido
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
