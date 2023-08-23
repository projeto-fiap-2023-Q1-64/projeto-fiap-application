package br.fiap.projeto.contexto.pagamento.adapter.controller;

import br.fiap.projeto.contexto.pagamento.adapter.controller.rest.port.IBuscaPagamentoRestAdapterController;
import br.fiap.projeto.contexto.pagamento.adapter.controller.rest.response.PagamentoDTOResponse;
import br.fiap.projeto.contexto.pagamento.entity.Pagamento;
import br.fiap.projeto.contexto.pagamento.entity.enums.StatusPagamento;
import br.fiap.projeto.contexto.pagamento.usecase.exceptions.ResourceNotFoundException;
import br.fiap.projeto.contexto.pagamento.usecase.port.usecase.IBuscaPagamentoUseCase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public class BuscaPagamentoRestAdapterController implements IBuscaPagamentoRestAdapterController {

    private final IBuscaPagamentoUseCase buscaPagamentoUseCase;

    public BuscaPagamentoRestAdapterController(IBuscaPagamentoUseCase buscaPagamentoUseCase) {
        this.buscaPagamentoUseCase = buscaPagamentoUseCase;
    }

    @Override
    public Page<PagamentoDTOResponse> findAll(Pageable pageable) {
        Page<Pagamento> listaDePagamentos = buscaPagamentoUseCase.findAll(pageable);
        return listaDePagamentos.map(PagamentoDTOResponse::new);
    }

    @Override
    public PagamentoDTOResponse findByCodigo(UUID codigo) {
        Optional<Pagamento> possivelPagamento = Optional.ofNullable(buscaPagamentoUseCase.findByCodigo(codigo));
        Pagamento pagamento = possivelPagamento.orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado"));
        return new PagamentoDTOResponse(pagamento);
    }

    @Override
    public Page<PagamentoDTOResponse> findByStatusPagamento(StatusPagamento status, Pageable pageable) {
        Page<Pagamento> listaDePagamentos = buscaPagamentoUseCase.findByStatusPagamento(status, pageable);
        return listaDePagamentos.map(PagamentoDTOResponse::new);
    }

    @Override
    public PagamentoDTOResponse findByCodigoPedido(String codigoPedido) {
        Optional<Pagamento> possivelPagamento = Optional.ofNullable(buscaPagamentoUseCase.findByCodigoPedido(codigoPedido));
        Pagamento pagamento = possivelPagamento.orElseThrow(() -> new ResourceNotFoundException("Pedido com ID " + codigoPedido + " não foi encontrado."));
        return new PagamentoDTOResponse(pagamento);
    }
}
