package br.com.alura.loja.dao;

import br.com.alura.loja.modelo.Produto;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

//Toda a parte de persistencia do produto vai ficar na classe ProdutoDao
public class ProdutoDao {

    private EntityManager em;

    public ProdutoDao(EntityManager em) {
        this.em = em;
    }

    public void cadastrar(Produto produto) {
        this.em.persist(produto);
    }

    public void deletar(Produto produto) {
        Produto produtoRef = em.merge(produto);
        this.em.remove(produtoRef);
    }

    public Produto buscarPorId(Long id) {
        return em.find(Produto.class, id);
    }

    public List<Produto> buscarTodosProdutos() {
        return em.createQuery("SELECT p FROM Produto p",Produto.class).getResultList();
    }

    public List<Produto> buscarPorNomeDoProduto(String parametro) {
        //p.nome - é o nome do atributo da entidade e não da coluna da tabela
        return em.createQuery("SELECT p FROM Produto p WHERE p.nome = :nome",Produto.class)
                .setParameter("nome", parametro)
                .getResultList();
    }

    public List<Produto> buscarPorNomeDaCategoria(String parametro) {
        // p.categoria.nome - A JPA entenderá que a categoria é um atributo da classe produto e, neste caso, um relacionamento.
        // Então, ele quer filtrar por um atributo dentro do relacionamento, desta maneira, a JPA automaticamente gerará um join,
        // isto é, ela já sabe que deve filtrar pelo relacionamento e faz o join automaticamente, evitando que seja necessário fazer manualmente, como seria no SQL.
        return em.createQuery("SELECT p FROM Produto p WHERE p.categoria.nome = :nome",Produto.class)
                .setParameter("nome", parametro)
                .getResultList();
    }

    public BigDecimal buscarPrecoDoProdutoPorNome(String parametro) {
        //p.nome - é o nome do atributo da entidade e não da coluna da tabela
        return em.createQuery("SELECT p.preco FROM Produto p WHERE p.nome = :nome", BigDecimal.class)
                .setParameter("nome", parametro)
                .getSingleResult();
    }
}
