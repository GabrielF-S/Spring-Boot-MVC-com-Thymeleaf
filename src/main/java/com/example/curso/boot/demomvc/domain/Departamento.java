package com.example.curso.boot.demomvc.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name = "DEPARTAMENTOS")
public class Departamento extends AbstractEntity<Long> {

    @NotBlank(message = "Informe um nome. ")
    @Size(min = 3, max = 60, message = "O nome do departamento deve ter entre {min} e {max} caracterers. ")
    @Column(name = "nome", nullable = false, unique = true, length = 60)
    private String nome;
    @OneToMany(mappedBy = "departamento")
    private List<Cargo> cargo;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Cargo> getCargo() {
        return cargo;
    }

    public void setCargo(List<Cargo> cargo) {
        this.cargo = cargo;
    }
}
