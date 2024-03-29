package com.example.curso.boot.demomvc.web.conversor;

import com.example.curso.boot.demomvc.domain.Departamento;
import com.example.curso.boot.demomvc.service.DepartamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToDepartamentoCoverter implements Converter<String, Departamento> {
    @Autowired
    private DepartamentoService departamentoService;
    @Override
    public Departamento convert(String text) {
        if(text.isEmpty()){
            return null;
        }
        Long id = Long.valueOf(text);
        return departamentoService.buscarPorId(id);
    }
}
