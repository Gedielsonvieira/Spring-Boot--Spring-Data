package br.com.alura.spring.data.orm;

/*
Gerando o anco de dados:

Então veja, com muito pouco, só criando uma entidade, só colocando algumas anotações, o Spring Data já gera essa
tabela também no nosso banco de dados, por isso que traz uma facilidade gigante utilizar esse Framework

Minha dúvida - quando fiz o curso de java e jpa foi a mesma coisa, simplesmente coloqueis as anotações e foi gerado a
tabela sendo que não estavamos utilizando o sprin data, e a diferença é que tinhamos utilizado um banco de dados em
memória, então quem faz a criação da tabela o spring data ou a jpa porque no projeto passado não utilizamos o spring data
e foi gerados as tabelas a partir das anotaç~eos?

*/
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GenerationType;


@Entity
@Table(name = "cargos")
public class Cargo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String descricao;

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
}
