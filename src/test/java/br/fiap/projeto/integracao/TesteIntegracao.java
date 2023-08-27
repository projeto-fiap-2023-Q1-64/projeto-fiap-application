package br.fiap.projeto.integracao;

import br.fiap.projeto.config.CustomPageImpl;
import br.fiap.projeto.contexto.comanda.domain.dto.ComandaDTO;
import br.fiap.projeto.contexto.comanda.domain.enums.StatusComanda;
import br.fiap.projeto.contexto.pagamento.adapter.controller.rest.request.PedidoAPagarDTORequest;
import br.fiap.projeto.contexto.pagamento.adapter.controller.rest.response.PagamentoDTOResponse;
import br.fiap.projeto.contexto.pagamento.adapter.controller.rest.response.PagamentoStatusDTOResponse;
import br.fiap.projeto.contexto.pagamento.entity.enums.StatusPagamento;
import br.fiap.projeto.contexto.pedido.application.rest.response.PedidoDTO;
import br.fiap.projeto.contexto.pedido.domain.enums.StatusPedido;
import br.fiap.projeto.contexto.produto.adapter.controller.rest.response.ProdutoDTOResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.test.context.TestPropertySource;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.net.URI;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.platform.commons.function.Try.success;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestPropertySource("classpath:application.properties")
@Slf4j
public class TesteIntegracao {
    private TestRestTemplate restTemplate;

    @BeforeEach
    public void setup() {

        restTemplate = new TestRestTemplate();
        restTemplate.getRestTemplate().setRequestFactory(new HttpComponentsClientHttpRequestFactory());
    }

    @Test
    public void test() throws Exception {

        PedidoDTO pedido;
        String codigoPedido;
        List<ProdutoDTOResponse> produtos;
        PedidoAPagarDTORequest pedidoAPagar;
        HttpEntity<?> httpEntity;
        Page<PagamentoDTOResponse> pagamentosAprovados;
        PagamentoDTOResponse pagamentoPedido;
        PagamentoStatusDTOResponse pagamentoStatusDTOResponse;
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
                        .anyMatch(y -> y.getCodigo().equals(x.getCodigo().getProdutoCodigo().toString()))
                )
        );

        // Altera status do pedido para recebido - @PatchMapping("/{codigo}/pagar")
        pedido = restTemplate.patchForObject(createUriWithPort("/pedidos/" + codigoPedido + "/pagar"), null, PedidoDTO.class);
        assertNotNull(pedido);
        assertNotNull(pedido.getStatus());
        assertTrue(pedido.getStatus().equals(StatusPedido.RECEBIDO));

        // Enviar para o gateway - PagamentosAprocessarController /url-do-gateway
        pedidoAPagar = new PedidoAPagarDTORequest(codigoPedido, pedido.getValorTotal());
        //pedidoAPagar.setStatusPagamento(StatusPagamento.PENDING);
        httpEntity = new HttpEntity<>(pedidoAPagar);
        restTemplate.exchange(createUriWithPort("/pagamento/gateway/url-do-gateway"), HttpMethod.POST, httpEntity, Void.class);

        // Recupera pagamentos aprovados - /por-status/{status}
        pagamentosAprovados = restTemplate.exchange(createUriWithPort("/pagamentos/por-status/" + StatusPagamento.IN_PROCESS), HttpMethod.GET, null,new ParameterizedTypeReference<CustomPageImpl<PagamentoDTOResponse>>(){}).getBody();
        assertNotNull(pagamentosAprovados);

        pagamentoPedido = pagamentosAprovados.stream()
                .filter(p -> codigoPedido.equals(p.getCodigoPedido()))
                .findFirst()
                .orElse(null);
        assertNotNull(pagamentoPedido);

         // Muda o status para aprovado - @PatchMapping(value="/atualiza-pagamento/{codigo}")
         pagamentoStatusDTOResponse = new PagamentoStatusDTOResponse(pagamentoPedido.getCodigo(), StatusPagamento.APPROVED);
         httpEntity = new HttpEntity(pagamentoStatusDTOResponse);
         restTemplate.patchForObject(createUriWithPort("/pagamentos/atualiza-pagamento/" + pagamentoStatusDTOResponse.getCodigo().toString()), httpEntity, Void.class);

         // Valida se o status alterou para aprovado
        pagamentosAprovados = restTemplate.exchange(createUriWithPort("/pagamentos/por-status/" + StatusPagamento.APPROVED), HttpMethod.GET, null,new ParameterizedTypeReference<CustomPageImpl<PagamentoDTOResponse>>(){}).getBody();
        assertNotNull(pagamentosAprovados);

        pagamentoPedido = pagamentosAprovados.stream()
                .filter(p -> codigoPedido.equals(p.getCodigoPedido()))
                .findFirst()
                .orElse(null);
        assertNotNull(pagamentoPedido);
        assertEquals(StatusPagamento.APPROVED, pagamentoPedido.getStatus());

        // Pedido atualiza o status dele (pra pago) -> /{codigo}/verificar-pagamento"
        pedido = restTemplate.exchange(createUriWithPort("/pedidos/" + codigoPedido + "/verificar-pagamento"), HttpMethod.PATCH, null, PedidoDTO.class).getBody();
        assertNotNull(pedido);
        assertEquals(StatusPedido.PAGO, pedido.getStatus());

        // Pedido cria a comanda => @PatchMapping("/{codigo}/enviar-comanda")
        pedido = restTemplate.exchange(createUriWithPort("/pedidos/" + codigoPedido + "/enviar-comanda"), HttpMethod.PATCH, null, PedidoDTO.class).getBody();
        assertNotNull(pedido);
        assertEquals(StatusPedido.EM_PREPARACAO, pedido.getStatus());

        // Busca comandas pendentes => @GetMapping("/comandas/busca-pendentes")
        comandasPendentes = restTemplate.exchange(createUriWithPort("/comandas/busca-pendentes"), HttpMethod.GET, null, new ParameterizedTypeReference<List<ComandaDTO>>(){}).getBody();
        assertNotNull(comandasPendentes);
        assertFalse(CollectionUtils.isEmpty(comandasPendentes));

        comanda = comandasPendentes.stream()
                .filter(c -> codigoPedido.equals(c.getCodigoPedido().toString()))
                .findFirst()
                .orElse(null);
        assertNotNull(comanda);

        // Comanda atualiza o status da comanda para preparação
        comanda = restTemplate.exchange(createUriWithPort("/comandas/" + comanda.getCodigoComanda() + "/preparar"), HttpMethod.PATCH, null, ComandaDTO.class).getBody();
        assertNotNull(comanda);
        assertEquals(StatusComanda.EM_PREPARACAO, comanda.getStatus());

        // Comanda atualiza o status da comanda para finalizado =>  @PatchMapping("/{codigo-comanda}/finalizar")
        // Ao mesmo tempo, comanda atualiza o status do pedido para pronto =>  @PutMapping("/{codigo}/prontificar")
        comanda = restTemplate.exchange(createUriWithPort("/comandas/" + comanda.getCodigoComanda() + "/finalizar"), HttpMethod.PATCH, null, ComandaDTO.class).getBody();
        assertNotNull(comanda);
        assertEquals(StatusComanda.FINALIZADO, comanda.getStatus());

        // Pedido chama rotina de entrega => @PatchMapping("/{codigo}/entregar")
        pedido = restTemplate.exchange(createUriWithPort("/pedidos/" + codigoPedido + "/entregar"), HttpMethod.PATCH, null, PedidoDTO.class).getBody();
        assertNotNull(pedido);
        assertEquals(StatusPedido.FINALIZADO, pedido.getStatus());

        success("Sucesso galera!");
    }

    private URI createUriWithPort(String s) {

        return URI.create("http://localhost:8080/" + s);
    }
}
