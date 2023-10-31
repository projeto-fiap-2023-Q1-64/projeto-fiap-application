package br.fiap.projeto.contexto.pedido;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.fiap.projeto.contexto.pedido.entity.Pedido;
import br.fiap.projeto.contexto.pedido.entity.enums.StatusPedido;
import br.fiap.projeto.contexto.pedido.usecase.PedidoStatusDataComparator;
import br.fiap.projeto.contexto.pedido.usecase.exception.InvalidStatusException;
import br.fiap.projeto.contexto.pedido.usecase.exception.NoItensException;

public class PedidoStatusDataComparatorServiceTest {

        private PedidoStatusDataComparator comparator;

        @BeforeEach
        public void setUp() {
                comparator = new PedidoStatusDataComparator();
        }

        @Test
        public void testComparePedidosComMesmaPrioridade() throws InvalidStatusException, NoItensException {
                Pedido pedido1 = new Pedido(UUID.randomUUID(), null, UUID.randomUUID(), StatusPedido.EM_PREPARACAO, 20d,
                                LocalDateTime.now());
                Pedido pedido2 = new Pedido(UUID.randomUUID(), null, UUID.randomUUID(), StatusPedido.EM_PREPARACAO, 20d,
                                LocalDateTime.now());

                int result = comparator.compare(pedido1, pedido2);

                assertEquals(0, result);
        }

        @Test
        public void testComparePedidosComDiferentePrioridade() throws InvalidStatusException, NoItensException {
                Pedido pedido1 = new Pedido(UUID.randomUUID(), null, UUID.randomUUID(), StatusPedido.EM_PREPARACAO, 20d,
                                LocalDateTime.now());
                Pedido pedido2 = new Pedido(UUID.randomUUID(), null, UUID.randomUUID(), StatusPedido.RECEBIDO, 20d,
                                LocalDateTime.now());

                int result = comparator.compare(pedido1, pedido2);

                assertTrue(result < 0);
        }

        @Test
        public void testComparePedidosComDiferenteStatusEMesmaPrioridade()
                        throws InvalidStatusException, NoItensException {
                Pedido pedido1 = new Pedido(UUID.randomUUID(), null, UUID.randomUUID(), StatusPedido.EM_PREPARACAO, 20d,
                                LocalDateTime.now());
                Pedido pedido2 = new Pedido(UUID.randomUUID(), null, UUID.randomUUID(), StatusPedido.RECEBIDO, 20d,
                                LocalDateTime.now());

                int result = comparator.compare(pedido1, pedido2);

                assertTrue(result < 0);
        }

        @Test
        public void testComparePedidosComDiferenteStatusEMaiorPrioridade()
                        throws InvalidStatusException, NoItensException {
                Pedido pedido1 = new Pedido(UUID.randomUUID(), null, UUID.randomUUID(), StatusPedido.EM_PREPARACAO, 20d,
                                LocalDateTime.now());
                Pedido pedido2 = new Pedido(UUID.randomUUID(), null, UUID.randomUUID(), StatusPedido.RECEBIDO, 20d,
                                LocalDateTime.now());

                int result = comparator.compare(pedido1, pedido2);

                assertTrue(result < 0);
        }

        @Test
        public void testComparePedidosComDiferenteStatusEPrioridadeMaiorQueZero()
                        throws InvalidStatusException, NoItensException {
                Pedido pedido1 = new Pedido(UUID.randomUUID(), null, UUID.randomUUID(), StatusPedido.PRONTO, 20d,
                                LocalDateTime.now());
                Pedido pedido2 = new Pedido(UUID.randomUUID(), null, UUID.randomUUID(), StatusPedido.RECEBIDO, 20d,
                                LocalDateTime.now());

                int result = comparator.compare(pedido1, pedido2);

                assertTrue(result < 0);
        }
}
