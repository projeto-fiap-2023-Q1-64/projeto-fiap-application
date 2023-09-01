package br.fiap.projeto.contexto.pagamento.external.api;

import br.fiap.projeto.contexto.pagamento.adapter.controller.rest.port.IBuscaPagamentoRestAdapterController;
import br.fiap.projeto.contexto.pagamento.adapter.controller.rest.response.PagamentoDTOResponse;
import br.fiap.projeto.contexto.pagamento.entity.enums.StatusPagamento;
import br.fiap.projeto.contexto.pagamento.usecase.exceptions.ResourceNotFoundException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
@Api(tags = {"Pagamentos"}, description = "Endpoints do domínio de Pagamentos")
public class PagamentoBuscaApiController {

    private final IBuscaPagamentoRestAdapterController buscaPagamentoRestAdapterController;

    @Autowired
    public PagamentoBuscaApiController(IBuscaPagamentoRestAdapterController buscaPagamentoRestAdapterController){
        this.buscaPagamentoRestAdapterController = buscaPagamentoRestAdapterController;
    }

    @GetMapping(value="/todos")
    @Transactional
    @ApiOperation(value = "Busca todos os pagamentos", notes="Esse endpoint recupera todos os pagamentos existentes na aplicação.")
    public ResponseEntity<List<PagamentoDTOResponse>> listaPagamentos(){
        return ResponseEntity.ok().body(buscaPagamentoRestAdapterController.findAll());
    }
    @GetMapping(value="/{codigoPagamento}")
    @Transactional
    @ApiOperation(value = "Busca pagamento com o código do pagamento", notes="Esse endpoint permite a busca de Pagamento usando o código do Pedido.")
    public ResponseEntity<PagamentoDTOResponse> buscaPagamentoPorCodigo(@ApiParam(value="Código do Pagamento") @PathVariable("codigoPagamento") UUID codigo){
        return ResponseEntity.ok().body(buscaPagamentoRestAdapterController.findByCodigo(codigo));
    }

    @GetMapping(value="/por-status/{status}")
    @Transactional
    @ApiOperation(value = "Busca pagamento(s) por Status do pagamento", notes="Esse endpoint permite a busca de Pagamento(s) filtrando por um Status de Pagamento.")
    public ResponseEntity <List<PagamentoDTOResponse>> listaDePagamentos(@ApiParam(value="Status do Pagamento") @PathVariable ("status") StatusPagamento status){
        return ResponseEntity.ok().body(buscaPagamentoRestAdapterController.findByStatusPagamento(status));
    }

    @GetMapping(value="/por-codigo-pedido/{codigoPedido}")
    @Transactional
    @ApiOperation(value = "Busca pagamento com o código do Pedido", notes="Esse endpoint permite a busca de Pagamento usando o código do Pedido.")
    public ResponseEntity<PagamentoDTOResponse> buscaStatusPagamentoPorCodigoPedido(@ApiParam(value="Código do Pedido") @PathVariable("codigoPedido") String codigoPedido ){
        return ResponseEntity.ok().body(buscaPagamentoRestAdapterController.findByCodigoPedido(codigoPedido));
    }

    @GetMapping(value="/aprovados")
    @Transactional
    @ApiOperation(value = "Busca pagamento(s) com Status Aprovado", notes="Esse endpoint permite buscar todos os pagamentos com Status Aprovados, a ser consumido pelo domínio de Pedidos.")
    public ResponseEntity <List<PagamentoDTOResponse>> buscaPagamentosAprovados(){
        return ResponseEntity.ok(buscaPagamentoRestAdapterController.findByStatusPagamento(StatusPagamento.APPROVED));
    }
}
