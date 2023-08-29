package br.fiap.projeto.contexto.comanda.adapter.gateway;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import br.fiap.projeto.contexto.comanda.adapter.gateway.portGateway.IBuscaStatusComandaGatewayAdapter;
import br.fiap.projeto.contexto.comanda.entity.Comanda;
import br.fiap.projeto.contexto.comanda.entity.enums.StatusComanda;
import br.fiap.projeto.contexto.comanda.external.exception.ExceptionMessage;
import br.fiap.projeto.contexto.comanda.external.repository.entity.ComandaEntity;
import br.fiap.projeto.contexto.comanda.external.repository.postgres.SpringComandaRepository;

@Component
@Primary
public class BuscaStatusPreparacaoComandaGatewayAdapter implements IBuscaStatusComandaGatewayAdapter {

    private final SpringComandaRepository springComandaRepository;

    @Autowired
    public BuscaStatusPreparacaoComandaGatewayAdapter(SpringComandaRepository springComandaRepository) {
        this.springComandaRepository = springComandaRepository;
    }

    @Override
    public List<Comanda> buscaComandaPorStatus(StatusComanda statusComanda) throws ExceptionMessage {
        List<ComandaEntity> resultados = springComandaRepository.findByStatus(statusComanda);
        List<Comanda> comandas = new ArrayList<>();
        if (!resultados.isEmpty()) {
            for (ComandaEntity comandaEntity : resultados) {
                comandas.add(comandaEntity.toComanda());
            }
            return comandas;
        }
        return null;
    }

}
