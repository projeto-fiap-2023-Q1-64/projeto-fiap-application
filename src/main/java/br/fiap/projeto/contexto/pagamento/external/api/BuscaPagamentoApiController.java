package br.fiap.projeto.contexto.pagamento.external.api;

import br.fiap.projeto.contexto.pagamento.adapter.controller.rest.port.IBuscaPagamentoRestAdapterController;
import br.fiap.projeto.contexto.pagamento.adapter.controller.rest.response.PagamentoDTOResponse;
import br.fiap.projeto.contexto.pagamento.entity.enums.StatusPagamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/pagamento/busca")
public class BuscaPagamentoApiController {

    private final IBuscaPagamentoRestAdapterController buscaPagamentoRestAdapterController;

    @Autowired
    public BuscaPagamentoApiController(IBuscaPagamentoRestAdapterController buscaPagamentoRestAdapterController){
        this.buscaPagamentoRestAdapterController = buscaPagamentoRestAdapterController;
    }

    @GetMapping(value="/todos")
    @Transactional
    public ResponseEntity<List<PagamentoDTOResponse>> listaPagamentos(){
        return ResponseEntity.ok().body(buscaPagamentoRestAdapterController.findAll());
    }
    @GetMapping(value="/{codigoPagamento}")
    @Transactional
    public ResponseEntity<PagamentoDTOResponse> buscaPagamentoPorCodigo(@PathVariable("codigoPagamento") UUID codigo){
        return ResponseEntity.ok().body( buscaPagamentoRestAdapterController.findByCodigo(codigo));
    }

    @GetMapping(value="/por-status/{status}")
    @Transactional
    public ResponseEntity <List<PagamentoDTOResponse>> listaDePagamentos(@PathVariable ("status") StatusPagamento status){
        return ResponseEntity.ok().body(buscaPagamentoRestAdapterController.findByStatusPagamento(status));
    }

    @GetMapping(value="/por-codigo-pedido/{codigoPedido}")
    @Transactional
    public ResponseEntity<PagamentoDTOResponse> buscaStatusPagamentoPorCodigoPedido(@PathVariable("codigoPedido") String codigoPedido ){
        return ResponseEntity.ok().body(buscaPagamentoRestAdapterController.findByCodigoPedido(codigoPedido));
    }
}
