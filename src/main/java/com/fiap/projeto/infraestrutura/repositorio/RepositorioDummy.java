package com.fiap.projeto.infraestrutura.repositorio;

import com.fiap.projeto.dominio.dummy.entidade.EntidadeDummy;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositorioDummy extends PagingAndSortingRepository<EntidadeDummy, Long> {

    public List<EntidadeDummy> findAll();
}
