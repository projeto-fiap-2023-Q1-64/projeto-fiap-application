package br.fiap.projeto.contexto.pagamento.application.rest;

import br.fiap.projeto.contexto.pagamento.application.rest.response.PagamentoAprovadoDTO;
import br.fiap.projeto.contexto.pagamento.application.rest.response.PagamentoDTO;
import br.fiap.projeto.contexto.pagamento.domain.port.service.PagamentoServicePort;
import com.sun.xml.bind.v2.TODO;
import io.swagger.models.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/pagamento")
public class PagamentosAprovadosController {

    private final PagamentoServicePort pagamentoServicePort;

    @Autowired
    public PagamentosAprovadosController(PagamentoServicePort pagamentoServicePort){
        this.pagamentoServicePort = pagamentoServicePort;
    }

    @GetMapping(value="/fila-pedidos-aprovados")
    @Transactional
    public ResponseEntity<Page<PagamentoAprovadoDTO>> pagamentosParaFilaDePedidos(Pageable pageable){
        Page<PagamentoAprovadoDTO> paginaPagamentosParaFileDePedidos = pagamentoServicePort.findByStatusAprovado(pageable);
        return ResponseEntity.ok().body(paginaPagamentosParaFileDePedidos);

    }

    //TODO implementar findByCodigoPedido para retornar o status do pagamento do pedido
    @GetMapping(value="/busca-por-pedido/{codigoPedido}")
    @Transactional
    public ResponseEntity<PagamentoAprovadoDTO> buscaStatusPagamentoPorCodigoPedido(@PathVariable("codigoPedido") String codigoPedido ){

        PagamentoDTO pagamentoDTO = pagamentoServicePort.findByCodigoPedido(codigoPedido);

        return ResponseEntity.ok().body(new PagamentoAprovadoDTO(pagamentoDTO));
    }
}
