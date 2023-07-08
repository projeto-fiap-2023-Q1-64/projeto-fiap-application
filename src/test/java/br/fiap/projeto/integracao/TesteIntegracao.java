package br.fiap.projeto.integracao;

import br.fiap.projeto.config.CustomPageImpl;
import br.fiap.projeto.contexto.pagamento.application.rest.response.PagamentoAprovadoDTO;
import br.fiap.projeto.contexto.pagamento.application.rest.response.PagamentoDTO;
import br.fiap.projeto.contexto.pagamento.application.rest.response.PagamentoStatusDTO;
import br.fiap.projeto.contexto.pagamento.application.rest.response.PedidoAPagarDTO;
import br.fiap.projeto.contexto.pagamento.domain.enums.StatusPagamento;
import br.fiap.projeto.contexto.pedido.application.rest.response.PedidoDTO;
import br.fiap.projeto.contexto.pedido.domain.enums.StatusPedido;
import br.fiap.projeto.contexto.produto.application.rest.response.ProdutoDTOResponse;
import com.fasterxml.jackson.core.type.TypeReference;
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
import java.util.UUID;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

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
        PedidoAPagarDTO pedidoAPagar;
        HttpEntity<?> httpEntity;
        Page<PagamentoDTO> pagamentosAprovados;
        PagamentoDTO pagamentoPedido;
        PagamentoStatusDTO pagamentoStatusDTO;

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
        pedidoAPagar = new PedidoAPagarDTO(codigoPedido, pedido.getValorTotal());
        pedidoAPagar.setStatusPagamento(StatusPagamento.PENDING);
        httpEntity = new HttpEntity<>(pedidoAPagar);
        restTemplate.exchange(createUriWithPort("/pagamento/gateway/url-do-gateway"), HttpMethod.POST, httpEntity, Void.class);

        // Recupera pagamentos aprovados - /por-status/{status}
        pagamentosAprovados = restTemplate.exchange(createUriWithPort("/pagamentos/por-status/" + StatusPagamento.IN_PROCESS), HttpMethod.GET, null,new ParameterizedTypeReference<CustomPageImpl<PagamentoDTO>>(){}).getBody();
        assertNotNull(pagamentosAprovados);

        pagamentoPedido = pagamentosAprovados.stream()
                .filter(p -> codigoPedido.equals(p.getCodigoPedido()))
                .findFirst()
                .orElse(null);
        assertNotNull(pagamentoPedido);

         // Muda o status para aprovado - @PatchMapping(value="/atualiza-pagamento/{codigo}")
         pagamentoStatusDTO = new PagamentoStatusDTO(pagamentoPedido.getCodigo(), StatusPagamento.APPROVED);
         httpEntity = new HttpEntity(pagamentoStatusDTO);
         restTemplate.patchForObject(createUriWithPort("/pagamentos/atualiza-pagamento/" + pagamentoStatusDTO.getCodigo().toString()), httpEntity, Void.class);

         // Valida se o status alterou para aprovado
        pagamentosAprovados = restTemplate.exchange(createUriWithPort("/pagamentos/por-status/" + StatusPagamento.APPROVED), HttpMethod.GET, null,new ParameterizedTypeReference<CustomPageImpl<PagamentoDTO>>(){}).getBody();
        assertNotNull(pagamentosAprovados);

        pagamentoPedido = pagamentosAprovados.stream()
                .filter(p -> codigoPedido.equals(p.getCodigoPedido()))
                .findFirst()
                .orElse(null);
        assertNotNull(pagamentoPedido);
        assertTrue(StatusPagamento.APPROVED.equals(pagamentoPedido.getStatus()));

        //

        log.info("Codigo pedido {}", codigoPedido);
    }

    private URI createUriWithPort(String s) {

        return URI.create("http://localhost:8080/" + s);
    }
}
