package br.com.alura.loja.modelo;

import javax.persistence.*;

@Entity
@Table(name = "categorias")
public class Categoria {
	@EmbeddedId
	private CategoriaPkComposta categoriaPkComposta;
	
	public Categoria() {
	}
	
	public Categoria(String nome, String tipo) {
		this.categoriaPkComposta = new CategoriaPkComposta(nome, "xpto");
	}

	public String getNome() {
		//isso se chama delegate
		return this.categoriaPkComposta.getNome();
	}


}
