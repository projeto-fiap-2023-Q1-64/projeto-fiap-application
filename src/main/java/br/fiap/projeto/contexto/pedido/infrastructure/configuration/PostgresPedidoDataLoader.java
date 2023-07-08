package br.fiap.projeto.contexto.pedido.infrastructure.configuration;

import br.fiap.projeto.contexto.pedido.application.rest.request.PedidoCriarDTO;
import br.fiap.projeto.contexto.pedido.domain.Pedido;
import br.fiap.projeto.contexto.pedido.infrastructure.repository.postgres.PostgresPedidoRepositoryPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class PostgresPedidoDataLoader {
    @Autowired
    private PostgresPedidoRepositoryPort pedidoRepository;
    @PostConstruct
    public void init(){
                // Criação de 2 produtos de teste
        PedidoCriarDTO pedidoCriarDTO = new PedidoCriarDTO();
        Pedido pedido = new Pedido(pedidoCriarDTO);
        pedidoRepository.salvar(pedido);
    }
}