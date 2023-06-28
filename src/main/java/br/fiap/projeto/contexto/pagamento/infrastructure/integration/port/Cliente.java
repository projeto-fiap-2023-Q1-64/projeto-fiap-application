package br.fiap.projeto.contexto.pagamento.infrastructure.integration.port;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {

    private String nome;
    private String email;
    private String cpf;
}
