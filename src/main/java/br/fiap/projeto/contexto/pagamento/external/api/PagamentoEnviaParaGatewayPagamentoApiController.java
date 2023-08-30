package br.fiap.projeto.contexto.pagamento.external.api;

import br.fiap.projeto.contexto.pagamento.adapter.controller.rest.port.IEnviaPagamentoGatewayRestAdapterController;
import br.fiap.projeto.contexto.pagamento.adapter.controller.rest.request.PagamentoAEnviarAoGatewayDTORequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pagamento/gateway")
public class PagamentoEnviaParaGatewayPagamentoApiController {

    private final IEnviaPagamentoGatewayRestAdapterController enviaPagamentoGatewayRestAdapterController;


    @Autowired
    public PagamentoEnviaParaGatewayPagamentoApiController(IEnviaPagamentoGatewayRestAdapterController enviaPagamentoGatewayRestAdapterController) {
        this.enviaPagamentoGatewayRestAdapterController = enviaPagamentoGatewayRestAdapterController;

    }

    @PostMapping(value="/gateway-de-pagamento")
    @Transactional
    public ResponseEntity<Void> enviaCompraParaGateway(@RequestBody PagamentoAEnviarAoGatewayDTORequest pagamentoAEnviarAoGatewayDTORequest) {
        enviaPagamentoGatewayRestAdapterController
                .enviaParaGatewayDePagamento(enviaPagamentoGatewayRestAdapterController
                        .preparaParaEnviarPagamentoAoGateway(pagamentoAEnviarAoGatewayDTORequest));
        return ResponseEntity.ok().build();
    }

}
