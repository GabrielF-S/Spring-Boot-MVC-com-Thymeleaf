package com.example.curso.boot.demomvc.service;

import com.example.curso.boot.demomvc.dao.CargoDao;
import com.example.curso.boot.demomvc.domain.Cargo;
import com.example.curso.boot.demomvc.util.PaginacaoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional(readOnly = false)
public class CargoServiceImpl implements CargoService{

    @Autowired
    private CargoDao dao;
    @Override
    public void salvar(Cargo cargo) {
        dao.save(cargo);

    }

    @Override
    public void editar(Cargo cargo) {
        dao.update(cargo);

    }

    @Override
    public void excluir(Long id) {
        dao.delete(id);

    }

    @Override
    @Transactional(readOnly = true)
    public Cargo buscarPorId(Long id) {
        return dao.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Cargo> buscarTodos() {
        return dao.findAll();
    }

    @Override
    public boolean cargoTemFuncionario(Long id) {
        if (buscarPorId(id).getFuncionarios().isEmpty()){
           return false;
        } else {
            return  true;
        }

    }

    @Override
    public PaginacaoUtil<Cargo> buscaPorPagina(int pagina) {
        return dao.buscaPaginada(pagina);
    }
}
