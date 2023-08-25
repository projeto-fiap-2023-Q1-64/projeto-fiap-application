package br.fiap.projeto.contexto.pedido.external.configuration;

import br.fiap.projeto.contexto.pedido.adapter.gateway.PedidoRepositoryAdapterGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PostgresPedidoDataLoader {
    @Autowired
    private PedidoRepositoryAdapterGateway pedidoRepository;

}