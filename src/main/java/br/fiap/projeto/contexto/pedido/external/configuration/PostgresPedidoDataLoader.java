package br.fiap.projeto.contexto.pedido.external.configuration;

import br.fiap.projeto.contexto.pedido.adapter.controller.rest.request.PedidoCriarDTO;
import br.fiap.projeto.contexto.pedido.entity.Pedido;
import br.fiap.projeto.contexto.pedido.adapter.gateway.PedidoRepositoryAdapterGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class PostgresPedidoDataLoader {
    @Autowired
    private PedidoRepositoryAdapterGateway pedidoRepository;
    @PostConstruct
    public void init(){
                // Criação de 2 produtos de teste
        PedidoCriarDTO pedidoCriarDTO = new PedidoCriarDTO();
        Pedido pedido = new Pedido(pedidoCriarDTO);
        pedidoRepository.salvar(pedido);
    }
}