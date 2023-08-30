package br.fiap.projeto.contexto.pagamento.external.api;

import br.fiap.projeto.contexto.pagamento.adapter.controller.rest.port.IBuscaPagamentoRestAdapterController;
import br.fiap.projeto.contexto.pagamento.adapter.controller.rest.response.PagamentoDTOResponse;
import br.fiap.projeto.contexto.pagamento.entity.enums.StatusPagamento;
import br.fiap.projeto.contexto.pagamento.usecase.exceptions.ResourceNotFoundException;
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
public class PagamentoBuscaApiController {

    private final IBuscaPagamentoRestAdapterController buscaPagamentoRestAdapterController;

    @Autowired
    public PagamentoBuscaApiController(IBuscaPagamentoRestAdapterController buscaPagamentoRestAdapterController){
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
        PagamentoDTOResponse possivelPagamentoDTOResponse;
        try{
            possivelPagamentoDTOResponse = buscaPagamentoRestAdapterController.findByCodigo(codigo);
        }
        catch(Exception e){
            throw new ResourceNotFoundException("Pagamento com código " + codigo + " inexistente.");
        }
        return ResponseEntity.ok().body(possivelPagamentoDTOResponse);
    }

    @GetMapping(value="/por-status/{status}")
    @Transactional
    public ResponseEntity <List<PagamentoDTOResponse>> listaDePagamentos(@PathVariable ("status") StatusPagamento status){
        return ResponseEntity.ok().body(buscaPagamentoRestAdapterController.findByStatusPagamento(status));
    }

    @GetMapping(value="/por-codigo-pedido/{codigoPedido}")
    @Transactional
    public ResponseEntity<PagamentoDTOResponse> buscaStatusPagamentoPorCodigoPedido(@PathVariable("codigoPedido") String codigoPedido ){
        PagamentoDTOResponse possivelPagamentoDTOResponse;
        try{
            possivelPagamentoDTOResponse = buscaPagamentoRestAdapterController.findByCodigoPedido(codigoPedido);
        }
        catch(Exception e){
            throw new ResourceNotFoundException("Pagamento com código de Pedido: " + codigoPedido + " inexistente.");
        }
        return ResponseEntity.ok().body(possivelPagamentoDTOResponse);
    }

    @GetMapping(value="/aprovados")
    @Transactional
    public ResponseEntity <List<PagamentoDTOResponse>> buscaPagamentosAprovados(){
        return ResponseEntity.ok(buscaPagamentoRestAdapterController.findByStatusPagamento(StatusPagamento.APPROVED));
    }
}
