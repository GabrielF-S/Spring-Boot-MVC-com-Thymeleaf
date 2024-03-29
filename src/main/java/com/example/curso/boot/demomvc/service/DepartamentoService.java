package com.example.curso.boot.demomvc.service;

import com.example.curso.boot.demomvc.domain.Cargo;
import com.example.curso.boot.demomvc.domain.Departamento;

import java.util.List;

public interface DepartamentoService {

    void salvar(Departamento departamento);


    void editar(Departamento departamento);

    void excluir(Long id);

    Departamento buscarPorId(Long id);

    List<Departamento> buscarTodos();

    boolean departamentoTemCargos(Long id);
}
