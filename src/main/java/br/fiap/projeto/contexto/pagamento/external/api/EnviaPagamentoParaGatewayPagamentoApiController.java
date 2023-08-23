package br.fiap.projeto.contexto.pagamento.external.api;

import br.fiap.projeto.contexto.pagamento.adapter.controller.rest.port.IEnviaPagamentoGatewayRestAdapterController;
import br.fiap.projeto.contexto.pagamento.adapter.controller.rest.request.PedidoAPagarDTORequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pagamento/gateway")
public class EnviaPagamentoParaGatewayPagamentoApiController {

    private final IEnviaPagamentoGatewayRestAdapterController enviaPagamentoGatewayRestAdapterController;


    @Autowired
    public EnviaPagamentoParaGatewayPagamentoApiController(IEnviaPagamentoGatewayRestAdapterController enviaPagamentoGatewayRestAdapterController) {
        this.enviaPagamentoGatewayRestAdapterController = enviaPagamentoGatewayRestAdapterController;

    }

    @PostMapping(value="/gateway-de-pagamento")
    @Transactional
    public ResponseEntity<Void> enviaCompraParaGateway(@RequestBody PedidoAPagarDTORequest pedidoAPagarDTORequest) {
        enviaPagamentoGatewayRestAdapterController
                .enviaParaGatewayDePagamento(enviaPagamentoGatewayRestAdapterController
                        .preparaParaEnviarPagamentoAoGateway(pedidoAPagarDTORequest));
        return ResponseEntity.ok().build();
    }

}
