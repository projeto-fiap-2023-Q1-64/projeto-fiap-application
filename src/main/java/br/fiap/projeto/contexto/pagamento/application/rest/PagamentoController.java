package br.fiap.projeto.contexto.pagamento.application.rest;

import br.fiap.projeto.contexto.pagamento.application.rest.response.PedidoAPagarDTO;
import br.fiap.projeto.contexto.pagamento.application.rest.response.PagamentoDTO;
import br.fiap.projeto.contexto.pagamento.application.rest.response.PagamentoStatusDTO;
import br.fiap.projeto.contexto.pagamento.domain.enums.StatusPagamento;
import br.fiap.projeto.contexto.pagamento.domain.port.service.PagamentoServicePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import java.net.URI;
import java.util.UUID;

/**
 * Simula os endpoints que podem ser utilizados para processamentos internos do domínio de pagamento
 */
@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {


    private final PagamentoServicePort pagamentoServicePort;

    @Autowired
    public PagamentoController(PagamentoServicePort pagamentoServicePort){
        this.pagamentoServicePort = pagamentoServicePort;
    }

    /**
     * Busca pagamento passando um código de pagamento existente
     * @param codigo
     * @return
     */
    @GetMapping(value="/{codigo}")
    @Transactional
    public ResponseEntity<PagamentoDTO> buscaPagamentoPorCodigo(@PathVariable("codigo") UUID codigo){
        PagamentoDTO pagamentoDTO = pagamentoServicePort.findByCodigo(codigo);
        return ResponseEntity.ok().body(pagamentoDTO);
    }

    /**
     * Busca todos os Pagamentos, sem filtro
     * @param pageable
     * @return
     */
    @GetMapping(value="/todos")
    @Transactional
    public ResponseEntity<Page<PagamentoDTO>> listaPagamentos(Pageable pageable){
        Page<PagamentoDTO> paginaComPagamentos = pagamentoServicePort.buscaListaDePagamentos(pageable);
        return ResponseEntity.ok().body(paginaComPagamentos);

    }

    /**
     * Busca todos os Pagamentos com filtro de Status
     * @param status
     * @param pageable
     * @return
     */
    @GetMapping(value="/por-status/{status}")
    @Transactional
    public ResponseEntity <Page<PagamentoDTO>> listaDePagamentos(@PathVariable ("status") StatusPagamento status, Pageable pageable){
        Page<PagamentoDTO> paginaPagamentoPorStatus = pagamentoServicePort.findByStatus(status, pageable);
        return ResponseEntity.ok().body(paginaPagamentoPorStatus);
    }

    /**
     * Simula a criação de um pagamento sem um código de pedido, utilizada aantes da Integração com o Gateway
     * @param pagamentoDTO
     * @return
     * @throws Exception
     */
    @PostMapping(value="/inicia-pagamento")
    @Transactional
    public ResponseEntity<PagamentoDTO> iniciaPagamento(@RequestBody PagamentoDTO pagamentoDTO) throws Exception {
        pagamentoServicePort.criaPagamento(pagamentoDTO);
        URI novoRecursoDePagamentoCriadoUri = ServletUriComponentsBuilder.fromCurrentRequest().path("{/codigo}").buildAndExpand(pagamentoDTO.getCodigoPedido()).toUri();
        return ResponseEntity.created(novoRecursoDePagamentoCriadoUri).body(pagamentoDTO);
    }

    /**
     * Atualiza os status de um Pagamento que está em processamento, simulando que o Gateway retorna a situação
     * e é passado no corpo da requisição o novo Status do Pagamento: APPROVED, REJECTED ou CANCELLED
     *
     * @param codigo
     * @param pagamentoStatusDTO
     * @return
     */
    @PatchMapping(value="/atualiza-pagamento/{codigo}")
    @Transactional
    public ResponseEntity<Void> atualizaPagamento(@PathVariable("codigo") UUID codigo, @RequestBody PagamentoStatusDTO pagamentoStatusDTO){
        pagamentoServicePort.processaPagamento(codigo, pagamentoStatusDTO.getStatus());
        return ResponseEntity.ok().build();
    }

}
