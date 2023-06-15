package com.fiap.projeto.dominio.dummy.entidade;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(schema = "test_schema", name = "test_table")
public class EntidadeDummy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long testIdCol;

    private String testCol;
}
