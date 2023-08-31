package br.fiap.projeto.contexto.pagamento.adapter.gateway;

import br.fiap.projeto.contexto.pagamento.entity.Pagamento;
import br.fiap.projeto.contexto.pagamento.external.repository.entity.PagamentoEntity;
import br.fiap.projeto.contexto.pagamento.external.repository.postgres.SpringPagamentoRepository;
import br.fiap.projeto.contexto.pagamento.usecase.port.repository.IEnviaPagamentoAoGatewayPagamentosRepositoryAdapterGateway;

import java.util.List;
import java.util.stream.Collectors;

public class EnviaPagamentoParaGatewayPagamentosRepositoryAdapterGateway implements IEnviaPagamentoAoGatewayPagamentosRepositoryAdapterGateway {

    private final SpringPagamentoRepository springPagamentoRepository;

    public EnviaPagamentoParaGatewayPagamentosRepositoryAdapterGateway(SpringPagamentoRepository springPagamentoRepository) {
        this.springPagamentoRepository = springPagamentoRepository;
    }

    @Override //TODO verificar uso do envia pelo UseCase
    public void persisteInfoDoPagamentoEnviadoAoGateway(Pagamento pagamento) {
        System.out.println("RepositoryAdapterGateway: ");
        System.out.println("Informações sobre o pagamento em andamento foram atualizadas.");
    }

    @Override //TODO preparar o que está em banco para despachar o request ao MP
    public List<Pagamento> preparaRequest(Pagamento pagamento) {

        List<PagamentoEntity> listaPagamentoEntity = springPagamentoRepository.findByCodigoPedido(pagamento.getCodigoPedido());

        return listaPagamentoEntity.stream().map(PagamentoEntity::conversorDePagamentoORMEntityParaPagamentoDomainEntity).collect(Collectors.toList());
    }
}
