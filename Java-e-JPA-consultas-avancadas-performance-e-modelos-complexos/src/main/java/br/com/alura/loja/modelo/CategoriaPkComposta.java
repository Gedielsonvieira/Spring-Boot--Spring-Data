package br.com.alura.loja.modelo;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class CategoriaPkComposta implements Serializable {
    private static final long serialVersionUID = 860168486131207651L;
    private String nome;
    private String tipo;

    public CategoriaPkComposta(String nome, String tipo) {
        this.nome = nome;
        this.tipo = tipo;
    }

    public CategoriaPkComposta() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
