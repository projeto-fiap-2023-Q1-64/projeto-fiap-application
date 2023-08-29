package br.fiap.projeto.contexto.comanda.adapter.gateway;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import br.fiap.projeto.contexto.comanda.adapter.gateway.portGateway.IAtualizaStatusComandaGatewayAdapter;
import br.fiap.projeto.contexto.comanda.entity.Comanda;
import br.fiap.projeto.contexto.comanda.external.exception.ExceptionMessage;
import br.fiap.projeto.contexto.comanda.external.repository.entity.ComandaEntity;
import br.fiap.projeto.contexto.comanda.external.repository.postgres.SpringComandaRepository;

@Component
@Primary
public class FinalizaComandaGatewayAdapter implements IAtualizaStatusComandaGatewayAdapter {

    private final SpringComandaRepository springComandaRepository;

    @Autowired
    public FinalizaComandaGatewayAdapter(SpringComandaRepository springComandaRepository) {
        this.springComandaRepository = springComandaRepository;
    }

    @Override
    public Comanda atualizar(UUID codigoComanda) throws ExceptionMessage {
        return springComandaRepository
                .save(new ComandaEntity(
                        new BuscaPorComandaGatewayAdapter(springComandaRepository).buscaPorComanda(codigoComanda)))
                .toComanda();
    }

}
