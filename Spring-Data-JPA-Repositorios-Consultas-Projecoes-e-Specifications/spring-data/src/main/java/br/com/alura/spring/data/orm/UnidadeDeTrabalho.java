package br.com.alura.spring.data.orm;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "unidade-de-trabalho")
public class UnidadeDeTrabalho {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String descricao;
    private String endereco;

    @ManyToMany(mappedBy = "unidadesDeTrabalho")
    List<Funcionario> funcionarios;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    @Override
    public String toString() {
        return "UnidadeDeTrabalho[" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                ", endereco='" + endereco + '\'' +
                ']';
    }
}