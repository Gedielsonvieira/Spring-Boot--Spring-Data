package br.com.alura.spring.data.orm;

import java.math.BigDecimal;

//O objetivo de criar essa interface é encapsular os valores de retorno da consulta dentro dos métodos.
public interface FuncionarioProjecaoInterface {
    Integer getId();
    String getNome();
    BigDecimal getSalario();
}
