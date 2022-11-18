package br.com.alura.loja.modelo;
//Utilizamos o import da JPA pois não queremos ficar presos ao Hibernate - a uma implementação - por isso desejamos usar
//o máximo possível a especificação, porque se um dia quisermos trocá-la, não teremos que mexer em todas as classes

import javax.persistence.*;
import java.math.BigDecimal;

//A classe produto representa a tabela de produto
@Entity//Essa anotação indica que essa classe é uma entidade da JPA, ou seja, tem uma tabela no BD à representando
@Table(name = "produtos")
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String descricao;
    private BigDecimal preco;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }
}
