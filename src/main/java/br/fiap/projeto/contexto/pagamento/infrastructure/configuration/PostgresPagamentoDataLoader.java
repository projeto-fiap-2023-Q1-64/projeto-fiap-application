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
                new Pagamento(null, "1", StatusPagamento.IN_PROCESS, new Date()),
                new Pagamento(null, "2", StatusPagamento.IN_PROCESS, new Date()),
                new Pagamento(null, "3", StatusPagamento.APPROVED, new Date()),
                new Pagamento(null, "4", StatusPagamento.APPROVED, new Date()),
                new Pagamento(null, "5", StatusPagamento.APPROVED, new Date()),
                new Pagamento(null, "6", StatusPagamento.APPROVED, new Date()),
                new Pagamento(null, "7", StatusPagamento.APPROVED, new Date()),
                new Pagamento(null, "8", StatusPagamento.APPROVED, new Date()),
                new Pagamento(null, "9", StatusPagamento.REJECTED, new Date()),
                new Pagamento(null, "10", StatusPagamento.CANCELLED, new Date()),
                new Pagamento(null, "11", StatusPagamento.PENDING, new Date()),
                new Pagamento(null, "12", StatusPagamento.PENDING, new Date()),
                new Pagamento(null, "13", StatusPagamento.PENDING, new Date())
                );

        pagamentoList.forEach(pagamento -> pagamentoRepository.salvaPagamento(pagamento));
    }

}
