package com.example.curso.boot.demomvc.web.controller;

import com.example.curso.boot.demomvc.domain.Cargo;
import com.example.curso.boot.demomvc.domain.Funcionario;
import com.example.curso.boot.demomvc.domain.UF;
import com.example.curso.boot.demomvc.service.CargoService;
import com.example.curso.boot.demomvc.service.FuncionarioService;
import com.example.curso.boot.demomvc.web.validator.FuncionarioValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/funcionarios")
public class FuncionarioController {
    @Autowired
    private FuncionarioService funcionarioService;
    @Autowired
    private CargoService cargoService;

    @GetMapping("/cadastrar")
    public String cadastrar(Funcionario funcionario){
        return "funcionario/cadastro";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.addValidators(new FuncionarioValidator());

    }
    @GetMapping("/listar")
    public String listar(ModelMap model){
        model.addAttribute("funcionarios", funcionarioService.buscarTodos());
        return "funcionario/lista";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid Funcionario funcionario, BindingResult result, RedirectAttributes attr){
        if (result.hasErrors()){
            return "funcionario/cadastro";
        }
        funcionarioService.salvar(funcionario);
        attr.addFlashAttribute("success","Funcionario inserido com sucesso.");
        return "redirect:/funcionarios/cadastrar";
    }

    @GetMapping("/editar/{id}")
    public String preEditar(@PathVariable("id") Long id, ModelMap model){
        model.addAttribute("funcionario", funcionarioService.buscarPorId(id));

        return "funcionario/cadastro";

    }
    @PostMapping("/editar")
    public String editar(@Valid Funcionario funcionario,BindingResult result, RedirectAttributes attr){
        if (result.hasErrors()){
            return "funcionario/cadastro";
        }
        funcionarioService.editar(funcionario);
        attr.addFlashAttribute("success","Registro editado com sucesso");
        return "redirect:/funcionarios/cadastrar";
    }
    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") Long id, RedirectAttributes attr){
        funcionarioService.excluir(id);
        attr.addFlashAttribute("success","Funcionario removido com sucesso.");
        return "redirect:/funcionarios/listar";

    }
    @GetMapping("/buscar/nome")
    public String getPorNome(@RequestParam("nome") String nome, ModelMap model){
        model.addAttribute("funcionarios", funcionarioService.buscarPorNome(nome));
        return "funcionario/lista";
    }

    @GetMapping("/buscar/cargo")
    public String getPorCargo(@RequestParam("id") Long id, ModelMap model){
        model.addAttribute("funcionarios", funcionarioService.buscarPorCargo(id));
        return "funcionario/lista";
    }
    @GetMapping("/buscar/data")
    public String getPorDatas(@RequestParam(value = "entrada", required = false) LocalDate entrada,
                              @RequestParam(value = "saida", required = false)  LocalDate saida,
                              ModelMap model){
        model.addAttribute("funcionarios", funcionarioService.buscarPorDatas(entrada, saida));
        return "funcionario/lista";
    }
    @ModelAttribute("cargos")
    public List<Cargo> getCargos(){
        return cargoService.buscarTodos();
    }
    @ModelAttribute("ufs")
    public UF[] getUf(){
        return UF.values();
    }

}
