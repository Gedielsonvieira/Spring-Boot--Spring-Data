package br.com.alura.loja.dao;



import br.com.alura.loja.modelo.Categoria;

import javax.persistence.EntityManager;

//Toda a parte de persistencia de categoria vai ficar na classe CategoriaDao
public class CategoriaDao {

    private EntityManager em;

    public CategoriaDao(EntityManager em) {
        this.em = em;
    }

    public void cadastrar(Categoria categoria){
        this.em.persist(categoria);
    }

    public void atualizar(Categoria categoria){
        this.em.merge(categoria);
    }
}
