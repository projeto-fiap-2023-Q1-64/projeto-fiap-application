package br.fiap.projeto.contexto.pagamento.usecase.port.usecase;

import br.fiap.projeto.contexto.pagamento.entity.Pagamento;

public interface IEnviaPagamentoAoGatewayPagamentosUseCase {

    //TODO adicionar o método enviar~ aqui -> será chamado o do useCase
    void enviaRequestAoSistemaExternoPagamentos(Pagamento pagamento);

}
