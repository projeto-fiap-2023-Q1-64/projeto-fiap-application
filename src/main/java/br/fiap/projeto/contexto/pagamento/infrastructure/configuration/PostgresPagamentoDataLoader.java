package br.fiap.projeto.contexto.pagamento.infrastructure.configuration;

import br.fiap.projeto.contexto.pagamento.domain.Pagamento;
import br.fiap.projeto.contexto.pagamento.domain.enums.StatusPagamento;
import br.fiap.projeto.contexto.pagamento.infrastructure.repository.postgres.PostgresPagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Configuration
public class PostgresPagamentoDataLoader {

    @Autowired
    private PostgresPagamentoRepository pagamentoRepository;

    @PostConstruct
    public void init(){
        List<Pagamento> pagamentoList = Arrays.asList(
                new Pagamento(null, 1L, StatusPagamento.IN_PROCESS, new Date()),
                new Pagamento(null, 2L, StatusPagamento.IN_PROCESS, new Date()),
                new Pagamento(null, 3L, StatusPagamento.APPROVED, new Date()),
                new Pagamento(null, 4L, StatusPagamento.APPROVED, new Date()),
                new Pagamento(null, 5L, StatusPagamento.APPROVED, new Date()),
                new Pagamento(null, 6L, StatusPagamento.APPROVED, new Date()),
                new Pagamento(null, 7L, StatusPagamento.APPROVED, new Date()),
                new Pagamento(null, 8L, StatusPagamento.APPROVED, new Date()),
                new Pagamento(null, 9L, StatusPagamento.REJECTED, new Date()),
                new Pagamento(null, 10L, StatusPagamento.CANCELLED, new Date()),
                new Pagamento(null, 11L, StatusPagamento.PENDING, new Date()),
                new Pagamento(null, 12L, StatusPagamento.PENDING, new Date()),
                new Pagamento(null, 13L, StatusPagamento.PENDING, new Date())
                );

        pagamentoList.forEach(pagamento -> pagamentoRepository.salvaPagamento(pagamento));
    }

}
