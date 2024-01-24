package com.example.curso.boot.demomvc.web.controller;

import com.example.curso.boot.demomvc.domain.Cargo;
import com.example.curso.boot.demomvc.domain.Departamento;
import com.example.curso.boot.demomvc.service.CargoService;
import com.example.curso.boot.demomvc.service.DepartamentoService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/cargos")
public class CargoController {
    @Autowired
    private CargoService cargoService;
    @Autowired
    private DepartamentoService departamentoService;

        @GetMapping("/cadastrar")
        public String cadastrar(Cargo cargo){
            return "/cargo/cadastro";
        }

        @GetMapping("/listar")
        public String listar(ModelMap model){
            model.addAttribute("cargos", cargoService.buscarTodos());
            return "/cargo/lista";
        }

        @PostMapping("/salvar")
        public String salvar(@Valid Cargo cargo, BindingResult result, RedirectAttributes attr){
            if (result.hasErrors()){
                return "cargo/cadastro";
            }

            cargoService.salvar(cargo);
            attr.addFlashAttribute("success","Cargo inserido com sucesso.");
            return "redirect:/cargos/cadastrar";
        }

    @GetMapping("/editar/{id}")
    public String preEditar(@PathVariable("id") Long id, ModelMap model){
        model.addAttribute("cargo", cargoService.buscarPorId(id));

        return "cargo/cadastro";

    }
    @PostMapping("/editar")
    public String editar(@Valid Cargo cargo, BindingResult result, RedirectAttributes attr){
        if (result.hasErrors()){
            return "cargo/cadastro";
        }

        cargoService.editar(cargo);
        attr.addFlashAttribute("success","Registro editado com sucesso");
        return "redirect:/cargos/cadastrar";
    }
    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") Long id, ModelMap model){
        if (cargoService.cargoTemFuncionario(id)){
            model.addAttribute("fail", "Cargo não removido. Possui cargo(s) vinculados");
        }else {
            cargoService.excluir(id);
            model.addAttribute("success", "Cargo  removido com sucesso");
        }
        return listar(model);
    }
        @ModelAttribute("departamentos")
        public List<Departamento> listDepatamentos(){
            return  departamentoService.buscarTodos();
        }




}
