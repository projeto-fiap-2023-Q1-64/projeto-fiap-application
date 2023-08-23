package br.fiap.projeto.contexto.pagamento.adapter.gateway;

import br.fiap.projeto.contexto.pagamento.entity.Pagamento;
import br.fiap.projeto.contexto.pagamento.external.repository.postgres.SpringPagamentoRepository;
import br.fiap.projeto.contexto.pagamento.usecase.port.repository.IEnviaPagamentoAoGatewayPagamentosRepositoryAdapterGateway;

public class EnviaPagamentoParaGatewayPagamentosRepositoryAdapterGateway implements IEnviaPagamentoAoGatewayPagamentosRepositoryAdapterGateway {

    private final SpringPagamentoRepository springPagamentoRepository;

    public EnviaPagamentoParaGatewayPagamentosRepositoryAdapterGateway(SpringPagamentoRepository springPagamentoRepository) {
        this.springPagamentoRepository = springPagamentoRepository;
    }

    @Override //TODO verificar uso do envia pelo UseCase
    public void envia(Pagamento pagamento) {
        System.out.println("Enviando pagamento para Gateway de Pagamento");
    }

    @Override //TODO preparar o que está em banco para despachar o request ao MP
    public Pagamento preparaRequest(Pagamento pagamento) {
        return new Pagamento(springPagamentoRepository.findByCodigoPedido(pagamento.getCodigoPedido()));
    }
}
