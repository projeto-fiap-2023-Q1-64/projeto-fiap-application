package com.fiap.projeto.dummy.presentation;

import com.fiap.projeto.dummy.application.DummyService;
import com.fiap.projeto.dummy.domain.entity.DummyEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dummy")
public class DummyController {

    @Autowired
    private DummyService dummyService;

    @GetMapping
    public List<DummyEntity> getDummyEntity() {

        return dummyService.getDummyEntities();
    }
}
