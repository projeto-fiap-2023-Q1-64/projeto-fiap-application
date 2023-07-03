package br.fiap.projeto.contexto.pedido.infrastructure.configuration;

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
        /*
        // Criação de 2 produtos de teste
        List<ProdutoPedido> list = Arrays.asList(
                new ProdutoPedido(UUID.fromString("28894d3e-5f18-40da-93c7-49440b911001"),
                        "Podrão",
                        "Tudo que tem direito",
                        20.0,
                        CategoriaProduto.LANCHE,
                        "https://via.placeholder.com/300/505",
                        12),
                new ProdutoPedido(UUID.fromString("28894d3e-5f18-40da-93c7-49440b911002")
                                                            , "Refrigerante de Guaraná 350ml"
                                                            , "Bebida gasosa a base de xarope de guaraná com aditivos que certamente destruirão o seu rim"
                                                            , 7.0
                                                            , CategoriaProduto.BEBIDA
                                                            , "https://via.placeholder.com/300/050"
                                                            , 12));
        list.stream().forEach(p -> produtoPedidoRepository.criaProdutoPedido(p));
        // Criação do pedido de testes
        Pedido pedido = new Pedido(UUID.fromString("28894d3e-5f18-40da-93c7-49440b912001")
                , null
                , UUID.fromString("28894d3e-5f18-40da-93c7-49440b913001")
                , StatusPedido.RECEBIDO
                , 0d
        );
        pedido.adicionarProduto(list.get(0));
        pedido.adicionarProduto(list.get(1));
        pedidoRepository.criaPedido(pedido);
        */
    }
}