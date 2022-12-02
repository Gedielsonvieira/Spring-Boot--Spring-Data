package br.com.alura.loja.modelo;

import javax.persistence.Embeddable;

//Informa para a JPA que essa classe é embutivel, ou seja, conseguimos embutir ela dentro de uma entidade (para que a
//JPA considere que esses atributos que não estão diretamente na entidade mesmo assim faça parte da tabela da entidade)
@Embeddable
public class DadosPessoais {
    private String nome;
    private String cpf;

    public DadosPessoais(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }

    public DadosPessoais() {
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }
}
