package br.fiap.projeto.contexto.pagamento.external.api;

import br.fiap.projeto.contexto.pagamento.adapter.controller.rest.port.IEnviaPagamentoGatewayRestAdapterController;
import br.fiap.projeto.contexto.pagamento.adapter.controller.rest.request.PagamentoAEnviarAoGatewayDTORequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pagamento/gateway")
@Api(tags = {"Pagamentos"}, description = "Endpoints do domínio de Pagamentos")
public class PagamentoEnviaParaGatewayPagamentoApiController {

    private final IEnviaPagamentoGatewayRestAdapterController enviaPagamentoGatewayRestAdapterController;


    @Autowired
    public PagamentoEnviaParaGatewayPagamentoApiController(IEnviaPagamentoGatewayRestAdapterController enviaPagamentoGatewayRestAdapterController) {
        this.enviaPagamentoGatewayRestAdapterController = enviaPagamentoGatewayRestAdapterController;

    }

    @PostMapping(value="/gateway-de-pagamento")
    @Transactional
    @ApiOperation(value = "Envia o Pagamento Gateway de Pagamentos", notes="Esse endpoint efetua o envio ao sistema externo de pagamentos - Integração com o Gateway que efetua a transação bancária.")
    public ResponseEntity<Void> enviaCompraParaGateway(@RequestBody PagamentoAEnviarAoGatewayDTORequest pagamentoAEnviarAoGatewayDTORequest) {

        PagamentoAEnviarAoGatewayDTORequest dto = enviaPagamentoGatewayRestAdapterController.preparaParaEnviarPagamentoAoGateway(pagamentoAEnviarAoGatewayDTORequest);
        enviaPagamentoGatewayRestAdapterController.enviaParaGatewayDePagamento(dto);
        return ResponseEntity.ok().build();
    }

}
