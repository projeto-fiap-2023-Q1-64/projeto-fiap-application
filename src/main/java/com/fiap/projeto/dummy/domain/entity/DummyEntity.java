package com.fiap.projeto.dummy.domain.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(schema = "test_schema", name = "test_table")
public class DummyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long testIdCol;
    private String testCol;
}
