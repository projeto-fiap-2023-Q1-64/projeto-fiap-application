package br.fiap.projeto.contexto.pedido.adapter.mapper;

import br.fiap.projeto.contexto.pedido.entity.integration.ComandaPedido;
import br.fiap.projeto.contexto.pedido.external.integration.port.Comanda;
import br.fiap.projeto.contexto.pedido.external.integration.port.CriaComanda;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ComandaMapper {
    public static CriaComanda toCriaComanda(UUID codigoPedido){
        return new CriaComanda(codigoPedido);
    }

    public static ComandaPedido toComandaPedido(Comanda comanda){
        return new ComandaPedido(comanda.getCodigoComanda(),
                comanda.getCodigoPedido());
    }
}
