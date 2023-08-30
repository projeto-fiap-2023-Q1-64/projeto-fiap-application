package br.fiap.projeto.contexto.pagamento.adapter.controller;

import br.fiap.projeto.contexto.pagamento.adapter.controller.rest.port.IBuscaPagamentoRestAdapterController;
import br.fiap.projeto.contexto.pagamento.adapter.controller.rest.response.PagamentoDTOResponse;
import br.fiap.projeto.contexto.pagamento.entity.Pagamento;
import br.fiap.projeto.contexto.pagamento.entity.enums.StatusPagamento;
import br.fiap.projeto.contexto.pagamento.usecase.exceptions.ResourceNotFoundException;
import br.fiap.projeto.contexto.pagamento.usecase.port.usecase.IBuscaPagamentoUseCase;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class BuscaPagamentoRestAdapterController implements IBuscaPagamentoRestAdapterController {

    private final IBuscaPagamentoUseCase buscaPagamentoUseCase;

    public BuscaPagamentoRestAdapterController(IBuscaPagamentoUseCase buscaPagamentoUseCase) {
        this.buscaPagamentoUseCase = buscaPagamentoUseCase;
    }

    @Override
    public List<PagamentoDTOResponse> findAll() {
        List<Pagamento> listaDePagamentos = buscaPagamentoUseCase.findAll();
        return listaDePagamentos.stream().map(PagamentoDTOResponse::new).collect(Collectors.toList());
    }

    @Override
    public PagamentoDTOResponse findByCodigo(UUID codigo) {
        Optional<Pagamento> possivelPagamento = Optional.ofNullable(buscaPagamentoUseCase.findByCodigo(codigo));
        Pagamento pagamento = possivelPagamento.orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado"));
        return new PagamentoDTOResponse(pagamento);
    }

    @Override
    public List<PagamentoDTOResponse> findByStatusPagamento(StatusPagamento status) {
        List<Pagamento> listaDePagamentos = buscaPagamentoUseCase.findByStatusPagamento(status);
        return listaDePagamentos.stream().map(PagamentoDTOResponse::new).collect(Collectors.toList());
    }

    @Override
    public PagamentoDTOResponse findByCodigoPedido(String codigoPedido) {
        Optional<Pagamento> possivelPagamento = Optional.ofNullable(buscaPagamentoUseCase.findByCodigoPedido(codigoPedido));
        Pagamento pagamento = possivelPagamento.orElseThrow(() -> new ResourceNotFoundException("Pedido com ID " + codigoPedido + " não foi encontrado."));
        return new PagamentoDTOResponse(pagamento);
    }
}
