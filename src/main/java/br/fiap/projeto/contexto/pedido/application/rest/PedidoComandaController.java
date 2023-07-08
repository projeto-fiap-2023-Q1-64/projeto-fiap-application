package br.fiap.projeto.contexto.pedido.application.rest;

import br.fiap.projeto.contexto.pedido.application.rest.response.PedidoDTO;
import br.fiap.projeto.contexto.pedido.domain.enums.StatusPagamento;
import br.fiap.projeto.contexto.pedido.domain.enums.StatusPedido;
import br.fiap.projeto.contexto.pedido.domain.port.service.PedidoService;
import br.fiap.projeto.contexto.pedido.infrastructure.integration.PedidoComandaIntegration;
import br.fiap.projeto.contexto.pedido.infrastructure.integration.port.Comanda;
import br.fiap.projeto.contexto.pedido.infrastructure.integration.port.CriaComanda;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/pedidos")
public class PedidoComandaController {
    private final PedidoService pedidoService;
    private final PedidoComandaIntegration pedidoComandaIntegration;
    @Autowired
    public PedidoComandaController(PedidoService pedidoService, PedidoComandaIntegration pedidoComandaIntegration) {
        this.pedidoService = pedidoService;
        this.pedidoComandaIntegration = pedidoComandaIntegration;
    }
    //-------------------------------------------------------------------------//
    //                        INTEGRAÇÃO COMANDA
    //-------------------------------------------------------------------------//
    @PatchMapping("/{codigo}/prontificar")
    @ResponseBody
    public ResponseEntity<PedidoDTO> prontificarPedido(@ApiParam(value="Código do Pedido") @PathVariable("codigo") UUID codigo) throws Exception {
        return ResponseEntity.ok().body(this.pedidoService.prontificar(codigo));
    }
    @PatchMapping("/{codigo}/enviar-comanda")
    @ResponseBody
    public ResponseEntity<PedidoDTO> enviarComanda(@ApiParam(value="Código do Pedido") @PathVariable("codigo") UUID codigo) throws Exception {
        PedidoDTO pedidoDTO = null;
        try{
            pedidoDTO = this.pedidoService.preparar(codigo);
            if(pedidoDTO == null || !pedidoDTO.getStatus().equals(StatusPedido.EM_PREPARACAO)){
                //TODO: tratar erro aqui
                System.out.println("Erro na atualização do status!");
                throw new Exception("Erro na atualização do status!");
            }
            Comanda comanda = pedidoComandaIntegration.criaComanda(new CriaComanda(codigo));
            // Verifica se criou comanda
            if(comanda == null || comanda.getCodigoComanda().toString().isEmpty()){
                //TODO: tratar erro aqui
                System.out.println("Erro na criação da comanda!");
                throw new Exception("Erro na criação da comanda!");
            }
        }catch (Exception e){
            //TODO: tratar erro aqui
            System.out.println("Erro na integração com a Comanda!");
            throw new Exception("Erro na integração com a Comanda!");
        }
        return ResponseEntity.ok().body(pedidoDTO);
    }
}
