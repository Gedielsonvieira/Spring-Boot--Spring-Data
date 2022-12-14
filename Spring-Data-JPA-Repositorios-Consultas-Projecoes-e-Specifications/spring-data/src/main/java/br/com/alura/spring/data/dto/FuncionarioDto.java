package br.com.alura.spring.data.dto;

import java.math.BigDecimal;

//O objetivo de criar essa classe é encapsular os valores de retorno da consulta dentro dos métodos.
public class FuncionarioDto {
    private Integer id;
    private String nome;
    private BigDecimal salario;

    public FuncionarioDto(Integer id, String nome, BigDecimal salario) {
        this.id = id;
        this.nome = nome;
        this.salario = salario;
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getSalario() {
        return salario;
    }

}