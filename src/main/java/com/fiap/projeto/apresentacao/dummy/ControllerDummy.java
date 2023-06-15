package com.fiap.projeto.apresentacao.dummy;

import com.fiap.projeto.aplicacao.dummy.ServicoDummy;
import com.fiap.projeto.dominio.dummy.entidade.EntidadeDummy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dummy")
public class ControllerDummy {

    @Autowired
    private ServicoDummy servicoDummy;

    @GetMapping
    public List<EntidadeDummy> getDummyEntity() {

        return servicoDummy.getDummyEntities();
    }
}
