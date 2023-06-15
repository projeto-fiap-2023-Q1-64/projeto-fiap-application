package com.fiap.projeto.aplicacao.dummy;

import com.fiap.projeto.dominio.dummy.entidade.EntidadeDummy;
import com.fiap.projeto.infraestrutura.repositorio.RepositorioDummy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicoDummy {

    @Autowired
    private RepositorioDummy repositorioDummy;

    public List<EntidadeDummy> getDummyEntities() {
        return repositorioDummy.findAll();
    }
}
