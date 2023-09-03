package br.fiap.projeto.contexto.pedido.external.integration.port;

import br.fiap.projeto.contexto.pagamento.external.integration.port.Pedido;
import br.fiap.projeto.contexto.pedido.entity.enums.StatusPagamento;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NovoPagamento {
    private String codigoPedido;
    private Double valorTotal;
    //private Date dataPagamento;
    //private List<Pedido> pedidos;
}
