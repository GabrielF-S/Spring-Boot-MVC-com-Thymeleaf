package com.example.curso.boot.demomvc.service;

import com.example.curso.boot.demomvc.domain.Cargo;
import com.example.curso.boot.demomvc.util.PaginacaoUtil;

import java.util.List;

public interface CargoService {

    void salvar(Cargo cargo);

    void editar(Cargo cargo);

    void excluir(Long id);

    Cargo buscarPorId(Long id);

    List<Cargo> buscarTodos();

    boolean cargoTemFuncionario(Long id);
    PaginacaoUtil<Cargo> buscaPorPagina(int pagina);


}
