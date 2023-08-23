package br.fiap.projeto.contexto.pagamento.external.integration;

import br.fiap.projeto.contexto.pagamento.external.integration.port.Pedido;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;


@FeignClient(value="pedidoIntegration", url = "http://localhost:8080/pedidos")
public interface PedidoIntegration {

    //TODO refatorar a recuperação de PedidosAPagar -> NovoUseCase?/NovoApiController? usar ProcessaNovoPagamentoUseCase?
    @RequestMapping(method = RequestMethod.GET, value = "/busca-recebidos")
    public List<Pedido> buscaPedidosAPagar();
}
