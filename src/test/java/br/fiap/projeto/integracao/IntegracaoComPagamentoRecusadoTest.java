package br.fiap.projeto.integracao;

import br.fiap.projeto.contexto.comanda.adapter.controller.rest.dto.ComandaDTO;
import br.fiap.projeto.contexto.comanda.entity.enums.StatusComanda;
import br.fiap.projeto.contexto.pagamento.adapter.controller.rest.request.PagamentoAEnviarAoGatewayDTORequest;
import br.fiap.projeto.contexto.pagamento.adapter.controller.rest.request.PagamentoStatusDTORequest;
import br.fiap.projeto.contexto.pagamento.adapter.controller.rest.request.PedidoAPagarDTORequest;
import br.fiap.projeto.contexto.pagamento.adapter.controller.rest.response.PagamentoDTOResponse;
import br.fiap.projeto.contexto.pagamento.entity.Pagamento;
import br.fiap.projeto.contexto.pagamento.entity.enums.StatusPagamento;
import br.fiap.projeto.contexto.pedido.adapter.controller.rest.response.PedidoDTO;
import br.fiap.projeto.contexto.pedido.entity.enums.StatusPedido;
import br.fiap.projeto.contexto.produto.adapter.controller.rest.response.ProdutoDTOResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.test.context.TestPropertySource;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.net.URI;
import java.util.Calendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.platform.commons.function.Try.success;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestPropertySource("classpath:application.properties")
@Slf4j
public class IntegracaoComPagamentoRecusadoTest {
    private static final Integer SERVER_PORT = 8080;
    private TestRestTemplate restTemplate;

    @BeforeEach
    public void setup() {

        restTemplate = new TestRestTemplate();
        restTemplate.getRestTemplate().setRequestFactory(new HttpComponentsClientHttpRequestFactory());
    }

    @Test
    public void test() {

        PedidoDTO pedido;
        String codigoPedido;
        List<ProdutoDTOResponse> produtos;
        PedidoAPagarDTORequest pedidoAPagar;
        HttpEntity<?> httpEntity;
        List<PagamentoDTOResponse> todosPagamentos;
        PagamentoDTOResponse pagamentoPedido;
        PagamentoAEnviarAoGatewayDTORequest pagamentoPendente;
        PagamentoStatusDTORequest pagamentoStatusDTO;
        ComandaDTO comanda;
        List<ComandaDTO> comandasPendentes;

        // Cria pedido
        pedido = restTemplate.postForEntity(createUriWithPort("/pedidos"), null, PedidoDTO.class).getBody();

        assertNotNull(pedido);
        assertNotNull(pedido.getCodigo());
        assertTrue(StringUtils.hasText(pedido.getCodigo().toString()));

        codigoPedido = pedido.getCodigo().toString();

        // Lista produtos existentes
        produtos = restTemplate.exchange(createUriWithPort("/produtos"), HttpMethod.GET, null, new ParameterizedTypeReference<List<ProdutoDTOResponse>>(){}).getBody();

        assertFalse(CollectionUtils.isEmpty(produtos));

        // Adiciona os produtos no pedido
        for (ProdutoDTOResponse p : produtos) {
            pedido = restTemplate.exchange(createUriWithPort("/pedidos/" + codigoPedido + "/adicionar-produto/" + p.getCodigo()), HttpMethod.POST, null, PedidoDTO.class).getBody();
        }
        assertNotNull(pedido);
        assertNotNull(pedido.getItens());
        assertTrue(pedido.getItens().stream()
                .allMatch(x -> produtos.stream()
                        .anyMatch(y -> y.getCodigo().equals(x.getProdutoCodigo().toString()))
                )
        );
        assertEquals(pedido.getCodigo().toString(), codigoPedido);

        // Altera status do pedido para recebido - @PutMapping("/{codigo}/pagar")
        pedido = restTemplate.exchange(createUriWithPort("/pedidos/" + codigoPedido + "/pagar"), HttpMethod.PUT, null, PedidoDTO.class).getBody();
        assertNotNull(pedido);
        assertNotNull(pedido.getStatus());
        assertEquals(pedido.getStatus(), StatusPedido.RECEBIDO);
        assertEquals(pedido.getCodigo().toString(), codigoPedido);

        /* TESTE DE PAGAMENTO RECUSADO */

        // Cria novo pagamento - PagamentoProcessaNovoApiController /pagamento/processa/novo-pagamento
        pedidoAPagar = new PedidoAPagarDTORequest(codigoPedido, pedido.getValorTotal());
        httpEntity = new HttpEntity<>(pedidoAPagar);
        restTemplate.exchange(createUriWithPort("/pagamento/processa/novo-pagamento"), HttpMethod.POST, httpEntity, Void.class);

        // Recupera pedido por código para verificação de status - /por-status/{status}
        // Valida se o estado alterou para PENDING
        pagamentoPedido = restTemplate.exchange(createUriWithPort("/pagamento/busca/por-codigo-pedido/" + codigoPedido), HttpMethod.GET, null, PagamentoDTOResponse.class).getBody();
        assertNotNull(pagamentoPedido);
        assertEquals(pagamentoPedido.getCodigoPedido(), codigoPedido);
        assertEquals(pagamentoPedido.getStatus(), StatusPagamento.PENDING);

        // Envia para o gateway de pagamento - status é alterado para em_processamento - /pagamento/gateway/gateway-de-pagamento
        pagamentoPendente = new PagamentoAEnviarAoGatewayDTORequest(new Pagamento(codigoPedido, pagamentoPedido.getValorTotal(), StatusPagamento.IN_PROCESS, Calendar.getInstance().getTime()));
        httpEntity = new HttpEntity<>(pagamentoPendente);
        restTemplate.postForObject(createUriWithPort("/pagamento/gateway/gateway-de-pagamento"), httpEntity, Void.class);

        // Recupera pedido por código para verificação de status - /por-status/{status}
        // Valida se o estado alterou para IN_PROCESS
        pagamentoPedido = restTemplate.exchange(createUriWithPort("/pagamento/busca/por-codigo-pedido/" + codigoPedido), HttpMethod.GET, null, PagamentoDTOResponse.class).getBody();
        assertNotNull(pagamentoPedido);
        assertEquals(pagamentoPedido.getCodigoPedido(), codigoPedido);
        assertEquals(pagamentoPedido.getStatus(), StatusPagamento.IN_PROCESS);

        // Chama endpoint de retorno do gateway -
        pagamentoStatusDTO = new PagamentoStatusDTORequest(codigoPedido, StatusPagamento.REJECTED);
        httpEntity = new HttpEntity<>(pagamentoStatusDTO);
        restTemplate.patchForObject(createUriWithPort("/pagamento/retorno-gateway/atualiza-status"), httpEntity, Void.class);

        // Recupera pedido por código para verificação de status - /por-status/{status}
        // Valida se o estado alterou para rejected
        ParameterizedTypeReference<List<PagamentoDTOResponse>> type = new ParameterizedTypeReference<List<PagamentoDTOResponse>>() {};
        ResponseEntity<List<PagamentoDTOResponse>> response = restTemplate.exchange(createUriWithPort("/pagamento/busca/por-status/" + StatusPagamento.REJECTED), HttpMethod.GET, null, type);
        todosPagamentos = response.getBody();
        assertNotNull(todosPagamentos);
        pagamentoPedido = todosPagamentos.stream()
                .filter(p -> p.getCodigoPedido().equals(codigoPedido))
                .filter(p -> p.getStatus().equals(StatusPagamento.REJECTED))
                .findFirst()
                .orElse(null);
        assertNotNull(pagamentoPedido);
        assertEquals(pagamentoPedido.getStatus(), StatusPagamento.REJECTED);

        /* TESTE SEGUNDA TENTATIVA DE PAGAMENTO */

        // Cria novo pagamento - PagamentoProcessaNovoApiController /pagamento/processa/novo-pagamento
        pedidoAPagar = new PedidoAPagarDTORequest(codigoPedido, pedido.getValorTotal());
        httpEntity = new HttpEntity<>(pedidoAPagar);
        restTemplate.exchange(createUriWithPort("/pagamento/processa/novo-pagamento"), HttpMethod.POST, httpEntity, Void.class);

        // Recupera pedido por código para verificação de status - /por-status/{status}
        // Valida se o estado alterou para PENDING
        pagamentoPedido = restTemplate.exchange(createUriWithPort("/pagamento/busca/por-codigo-pedido/" + codigoPedido), HttpMethod.GET, null, PagamentoDTOResponse.class).getBody();
        assertNotNull(pagamentoPedido);
        assertEquals(pagamentoPedido.getCodigoPedido(), codigoPedido);
        assertEquals(pagamentoPedido.getStatus(), StatusPagamento.PENDING);

        // Envia para o gateway de pagamento - status é alterado para em_processamento - /pagamento/gateway/gateway-de-pagamento
        pagamentoPendente = new PagamentoAEnviarAoGatewayDTORequest(new Pagamento(codigoPedido, pagamentoPedido.getValorTotal(), StatusPagamento.IN_PROCESS, Calendar.getInstance().getTime()));
        httpEntity = new HttpEntity<>(pagamentoPendente);
        restTemplate.postForObject(createUriWithPort("/pagamento/gateway/gateway-de-pagamento"), httpEntity, Void.class);

        // Recupera pedido por código para verificação de status - /por-status/{status}
        // Valida se o estado alterou para IN_PROCESS
        pagamentoPedido = restTemplate.exchange(createUriWithPort("/pagamento/busca/por-codigo-pedido/" + codigoPedido), HttpMethod.GET, null, PagamentoDTOResponse.class).getBody();
        assertNotNull(pagamentoPedido);
        assertEquals(pagamentoPedido.getCodigoPedido(), codigoPedido);
        assertEquals(pagamentoPedido.getStatus(), StatusPagamento.IN_PROCESS);

         // Chama endpoint de retorno do gateway -
         pagamentoStatusDTO = new PagamentoStatusDTORequest(codigoPedido, StatusPagamento.APPROVED);
         httpEntity = new HttpEntity<>(pagamentoStatusDTO);
         restTemplate.patchForObject(createUriWithPort("/pagamento/retorno-gateway/atualiza-status"), httpEntity, Void.class);

        // Recupera pedido por código para verificação de status - /por-status/{status}
        // Valida se o estado alterou para aprovado
        pagamentoPedido = restTemplate.exchange(createUriWithPort("/pagamento/busca/por-codigo-pedido/" + codigoPedido), HttpMethod.GET, null, PagamentoDTOResponse.class).getBody();
        assertNotNull(pagamentoPedido);
        assertEquals(pagamentoPedido.getStatus(), StatusPagamento.APPROVED);

        // Pedido atualiza o status dele (pra pago) -> /{codigo}/verificar-pagamento"
        pedido = restTemplate.exchange(createUriWithPort("/pedidos/" + codigoPedido + "/atualizar-pagamento"), HttpMethod.PATCH, null, PedidoDTO.class).getBody();
        assertNotNull(pedido);
        assertEquals(StatusPedido.PAGO, pedido.getStatus());

        // Pedido cria a comanda => @PatchMapping("/{codigo}/enviar-comanda")
        pedido = restTemplate.exchange(createUriWithPort("/pedidos/" + codigoPedido + "/enviar-comanda"), HttpMethod.PATCH, null, PedidoDTO.class).getBody();
        assertNotNull(pedido);
        assertEquals(StatusPedido.EM_PREPARACAO, pedido.getStatus());

        // Busca comandas pendentes => @GetMapping("/comandas/busca-pendentes")
        comandasPendentes = restTemplate.exchange(createUriWithPort("/comandas/busca-recebido"), HttpMethod.GET, null, new ParameterizedTypeReference<List<ComandaDTO>>(){}).getBody();
        assertNotNull(comandasPendentes);
        assertFalse(CollectionUtils.isEmpty(comandasPendentes));

        comanda = comandasPendentes.stream()
                .filter(c -> codigoPedido.equals(c.getCodigoPedido().toString()))
                .findFirst()
                .orElse(null);
        assertNotNull(comanda);

        // Comanda atualiza o estado da comanda para preparação
        comanda = restTemplate.exchange(createUriWithPort("/comandas/" + comanda.getCodigoComanda() + "/preparar"), HttpMethod.PATCH, null, ComandaDTO.class).getBody();
        assertNotNull(comanda);
        assertEquals(StatusComanda.EM_PREPARACAO, comanda.getStatus());

        // Comanda atualiza o status da comanda para finalizado =>  @PatchMapping("/{codigo-comanda}/finalizar")
        // Ao mesmo tempo, comanda atualiza o status do pedido para pronto =>  @PutMapping("/{codigo}/prontificar")
        comanda = restTemplate.exchange(createUriWithPort("/comandas/" + comanda.getCodigoComanda() + "/finalizar"), HttpMethod.PATCH, null, ComandaDTO.class).getBody();
        assertNotNull(comanda);
        assertEquals(StatusComanda.FINALIZADO, comanda.getStatus());

        // Pedido chama rotina de entrega => @PatchMapping("/{codigo}/entregar")
        pedido = restTemplate.exchange(createUriWithPort("/pedidos/" + codigoPedido + "/entregar"), HttpMethod.PUT, null, PedidoDTO.class).getBody();
        assertNotNull(pedido);
        assertEquals(StatusPedido.FINALIZADO, pedido.getStatus());

        success("Sucesso galera!");
    }

    private URI createUriWithPort(String s) {

        String sUrl = String.format("http:////localhost:%d/%s", SERVER_PORT, s).replace("//", "/");
        return URI.create(sUrl);
    }
}
