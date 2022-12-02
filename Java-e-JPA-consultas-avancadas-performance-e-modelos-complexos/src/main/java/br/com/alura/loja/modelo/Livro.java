package br.com.alura.loja.modelo;

import javax.persistence.Entity;
import javax.persistence.Table;

//Classe que representa um subproduto livro
@Entity
@Table(name = "livro")
public class Livro extends Produto{
    private String autor;
    private Integer numeroDePaginas;

    public Livro(String autor, Integer numeroDePaginas) {
        this.autor = autor;
        this.numeroDePaginas = numeroDePaginas;
    }
    //A JPA nos obriga a ter um construtor default
    public Livro() {
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Integer getNumeroDePaginas() {
        return numeroDePaginas;
    }

    public void setNumeroDePaginas(Integer numeroDePaginas) {
        this.numeroDePaginas = numeroDePaginas;
    }
}
