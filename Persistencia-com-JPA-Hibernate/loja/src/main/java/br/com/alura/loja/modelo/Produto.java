package br.com.alura.loja.modelo;
//Utilizamos o import da JPA pois não queremos ficar presos ao Hibernate - a uma implementação - por isso desejamos usar
//o máximo possível a especificação, porque se um dia quisermos trocá-la, não teremos que mexer em todas as classes

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

import static java.time.temporal.TemporalQueries.localDate;

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
    private LocalDate dateCadastro = LocalDate.now();

    /* Cardinalidade - Um produto tem uma única categoria e uma categoria pode estar vinculada a vários produtos
    Muitos produtos podem estar vinculados a uma categoria */
    @ManyToOne
    private Categoria categoria;

    public Produto(String nome, String descricao, BigDecimal preco, Categoria categoria) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.categoria = categoria;
    }

    public Produto(){}

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

    public LocalDate getDateCadastro() {
        return dateCadastro;
    }

    public void setDateCadastro(LocalDate dateCadastro) {
        this.dateCadastro = dateCadastro;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
