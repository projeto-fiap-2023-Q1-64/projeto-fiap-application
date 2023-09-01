package br.fiap.projeto.contexto.pedido.usecase;

import br.fiap.projeto.contexto.pedido.entity.Pedido;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class PedidoStatusDataComparator implements Comparator<Pedido> {
    @Override
    public int compare(Pedido o1, Pedido o2) {
        // Cria map e define ordem de prioridade dos status
        Map<String,Integer> mapStatus = new HashMap<>();
        mapStatus.put("PRONTO",1);
        mapStatus.put("EM_PREPARACAO",2);
        mapStatus.put("PAGO",3);
        mapStatus.put("RECEBIDO",4);

        // Compara se os 2 pedidos tem mesma prioridade no status
        if(mapStatus.get(o1.getStatus().name()).equals(mapStatus.get(o2.getStatus().name()))){
            return o1.getDataCriacao().compareTo(o2.getDataCriacao());
        }
        return mapStatus.get(o1.getStatus().name()).compareTo(mapStatus.get(o2.getStatus().name()));
    }
}
