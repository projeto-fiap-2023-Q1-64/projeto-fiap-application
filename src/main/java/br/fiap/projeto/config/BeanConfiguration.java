package br.fiap.projeto.config;

import br.fiap.projeto.contexto.pagamento.domain.port.repository.PagamentoRepositoryPort;
import br.fiap.projeto.contexto.pagamento.domain.port.service.PagamentoServicePort;
import br.fiap.projeto.contexto.pagamento.domain.service.DomainPagamentoService;
import br.fiap.projeto.contexto.pedido.domain.port.repository.PedidoRepositoryPort;
import br.fiap.projeto.contexto.pedido.domain.port.service.PedidoService;
import br.fiap.projeto.contexto.pedido.domain.service.DomainPedidoService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    PedidoService pedidoService(PedidoRepositoryPort pedidoRepositoryPort){
        return new DomainPedidoService(pedidoRepositoryPort);
    }

    @Bean
    PagamentoServicePort pagamentoService(PagamentoRepositoryPort pagamentoRepositoryPort){
        return new DomainPagamentoService(pagamentoRepositoryPort);
    }
}
