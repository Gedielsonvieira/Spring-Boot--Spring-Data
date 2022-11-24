package br.com.alura.loja.testes;

import br.com.alura.loja.dao.CategoriaDao;
import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

public class CadastroDeProdutos {
    public static void main(String[] args) {
        Categoria celulares = new Categoria("Celulares");

        //Produto celular = new Produto("Xiaomi Redmi", "Muito legal", new BigDecimal("800"), celulares);

        EntityManager em = JPAUtil.getEntityManager();
        //ProdutoDao produtoDao = new ProdutoDao(em);
        CategoriaDao categoriaDao = new CategoriaDao(em);

        em.getTransaction().begin();
        categoriaDao.cadastrar(celulares);
        //produtoDao.cadastrar(celular);
        em.getTransaction().commit();

        
        em.getTransaction().begin();
        Categoria categoria = em.find(Categoria.class, 1l);
        em.remove(categoria);
        em.getTransaction().commit();
        em.close();
    }
}
