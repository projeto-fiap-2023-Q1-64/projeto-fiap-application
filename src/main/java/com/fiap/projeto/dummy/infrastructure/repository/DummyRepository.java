package com.fiap.projeto.dummy.infrastructure.repository;

import com.fiap.projeto.dummy.domain.entity.DummyEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DummyRepository extends PagingAndSortingRepository<DummyEntity, Long> {

    public List<DummyEntity> findAll();
}
