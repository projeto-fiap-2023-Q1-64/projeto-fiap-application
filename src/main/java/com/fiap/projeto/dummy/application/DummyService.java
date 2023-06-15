package com.fiap.projeto.dummy.application;

import com.fiap.projeto.dummy.domain.entity.DummyEntity;
import com.fiap.projeto.dummy.infrastructure.repository.DummyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DummyService {

    @Autowired
    private DummyRepository dummyRepository;

    public List<DummyEntity> getDummyEntities() {
        return dummyRepository.findAll();
    }
}
