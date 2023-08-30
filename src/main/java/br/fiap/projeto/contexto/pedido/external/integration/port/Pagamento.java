package br.fiap.projeto.contexto.pedido.external.integration.port;

import br.fiap.projeto.contexto.pedido.entity.enums.StatusPagamento;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pagamento {
    private String codigoPedido;
    private Date dataPagamento;
    private StatusPagamento statusPagamento;
}
