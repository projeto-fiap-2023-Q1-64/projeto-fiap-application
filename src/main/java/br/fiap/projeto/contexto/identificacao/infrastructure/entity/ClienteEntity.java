package br.fiap.projeto.contexto.identificacao.infrastructure.entity;

import javax.persistence.*;


@Entity
@Table(name = "clientes")
public class ClienteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    private String nome;

    private String cpf;

    private String email;
}
