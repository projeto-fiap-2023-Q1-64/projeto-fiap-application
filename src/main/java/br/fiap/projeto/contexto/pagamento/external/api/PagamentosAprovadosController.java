package br.fiap.projeto.contexto.pagamento.external.api;

import br.fiap.projeto.contexto.pagamento.adapter.controller.rest.response.PagamentoAprovadoDTO;
import br.fiap.projeto.contexto.pagamento.adapter.controller.rest.response.PagamentoDTO;
import br.fiap.projeto.contexto.pagamento.usecase.port.service.PagamentoServicePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Simula o endpoint de externalização de dados do Pagamento de Pedidos
 * que podem ser consumidos por outros domínios
 */
@RestController
@RequestMapping(value="/pagamento")
public class PagamentosAprovadosController {

    private final PagamentoServicePort pagamentoServicePort;

    @Autowired
    public PagamentosAprovadosController(PagamentoServicePort pagamentoServicePort){
        this.pagamentoServicePort = pagamentoServicePort;
    }

    /**
     * #1 Retorna uma lista ("fila") de pagamentos aprovados, simulando a Fila de Pedidos
     * que foram pagos e que podem ser retornados ao domínio de Pedidos para dar continuidade no processo
     *
     * @param pageable
     * @return
     */
    @GetMapping(value="/fila-pedidos-aprovados")
    @Transactional
    public ResponseEntity<Page<PagamentoAprovadoDTO>> pagamentosParaFilaDePedidos(Pageable pageable){
        Page<PagamentoAprovadoDTO> paginaPagamentosParaFileDePedidos = pagamentoServicePort.findByStatusAprovado(pageable);
        return ResponseEntity.ok().body(paginaPagamentosParaFileDePedidos);

    }

    /**
     * #2 Permite a pesquisa de um status de pagamento através do código de Pedido, simulando a
     * interação entre o domínio de Pedido que verifica se o mesmo já foi pago e está Aprovado.
     *
     *TODO #1 Adicionar Exception para tratar caso o domínio de pedido consulte um pedido que ainda não existe dentro do
     * contexto de pagamento
     *
     *TODO #2 verificar o nome da DTO, a consulta nem sempre vai retornar um APROVADO (manter a consistência nos nomes)
     *
     * @param codigoPedido
     * @return
     */
    @GetMapping(value="/busca-por-pedido/{codigoPedido}")
    @Transactional
    public ResponseEntity<PagamentoAprovadoDTO> buscaStatusPagamentoPorCodigoPedido(@PathVariable("codigoPedido") String codigoPedido ){
        PagamentoDTO pagamentoDTO = pagamentoServicePort.findByCodigoPedido(codigoPedido);
        return ResponseEntity.ok().body(new PagamentoAprovadoDTO(pagamentoDTO));
    }
}
