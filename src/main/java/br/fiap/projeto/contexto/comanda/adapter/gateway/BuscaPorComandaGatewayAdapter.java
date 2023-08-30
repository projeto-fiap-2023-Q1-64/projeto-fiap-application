package br.fiap.projeto.contexto.comanda.adapter.gateway;

import java.util.Optional;
import java.util.UUID;

import br.fiap.projeto.contexto.comanda.entity.Comanda;
import br.fiap.projeto.contexto.comanda.external.exception.ExceptionMessage;
import br.fiap.projeto.contexto.comanda.external.repository.entity.ComandaEntity;
import br.fiap.projeto.contexto.comanda.external.repository.postgres.SpringComandaRepository;
import br.fiap.projeto.contexto.comanda.usecase.port.repositoryInterface.IBuscarPorComandaRepositoryUseCase;

// @Component
// @Primary
public class BuscaPorComandaGatewayAdapter implements IBuscarPorComandaRepositoryUseCase {

    private final SpringComandaRepository springComandaRepository;

    // @Autowired
    public BuscaPorComandaGatewayAdapter(SpringComandaRepository springComandaRepository) {
        this.springComandaRepository = springComandaRepository;
    }

    @Override
    public Comanda buscar(UUID codigoComanda) throws ExceptionMessage {
        Optional<ComandaEntity> comandaEntity = springComandaRepository.findById(codigoComanda);
        if (comandaEntity.isPresent()) {
            return comandaEntity.get().toComanda();
        }
        return null;

    }

}
