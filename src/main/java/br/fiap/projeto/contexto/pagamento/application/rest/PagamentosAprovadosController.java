package br.fiap.projeto.contexto.pagamento.application.rest;

import br.fiap.projeto.contexto.pagamento.application.rest.response.PagamentoAprovadoDTO;
import br.fiap.projeto.contexto.pagamento.domain.port.service.PagamentoServicePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/v1/fila")
public class PagamentosAprovadosController {

    private final PagamentoServicePort pagamentoServicePort;

    @Autowired
    public PagamentosAprovadosController(PagamentoServicePort pagamentoServicePort){
        this.pagamentoServicePort = pagamentoServicePort;
    }

    @GetMapping(value="/pedidos")
    @Transactional
    public ResponseEntity<Page<PagamentoAprovadoDTO>> pagamentosParaFilaDePedidos(Pageable pageable){
        Page<PagamentoAprovadoDTO> paginaPagamentosParaFileDePedidos = pagamentoServicePort.findByStatusAprovado(pageable);
        return ResponseEntity.ok().body(paginaPagamentosParaFileDePedidos);

    }
}
