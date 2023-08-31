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
        List<Pagamento> listaDePagamentosPorCodigoPedido = buscaPagamentoUseCase.findByCodigoPedido(codigoPedido);

        Optional<Pagamento> possivelPagamento = listaDePagamentosPorCodigoPedido
                .stream()
                .filter(pagamento -> codigoPedido.equals(pagamento.getCodigoPedido())
                        && !StatusPagamento.REJECTED.equals(pagamento.getStatus()))
                .findFirst();
        return new PagamentoDTOResponse(possivelPagamento.orElseThrow( () -> new ResourceNotFoundException("Pagamento não encontrado.") ));
    }

    @Override
    public PagamentoDTOResponse findByCodigoPedidoAtualizarStatus(String codigoPedido) {
        List<Pagamento> listaDePagamentosPorCodigoPedido = buscaPagamentoUseCase.findByCodigoPedido(codigoPedido);

        Optional<Pagamento> possivelPagamento = listaDePagamentosPorCodigoPedido
                .stream()
                .filter(pagamento -> codigoPedido.equals(pagamento.getCodigoPedido()))
                .findFirst();
        return new PagamentoDTOResponse(possivelPagamento.orElseThrow( () -> new ResourceNotFoundException("Pagamento não encontrado.") ));
    }
}
