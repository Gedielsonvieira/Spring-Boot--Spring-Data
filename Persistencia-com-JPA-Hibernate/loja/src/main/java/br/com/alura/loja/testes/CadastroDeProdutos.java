package br.com.alura.loja.testes;

import br.com.alura.loja.dao.CategoriaDao;
import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class CadastroDeProdutos {
    public static void main(String[] args) {
        cadastrarProduto();

        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(em);
        Produto consultaDoProdutoPorId = produtoDao.buscarPorId(1L);
        System.out.println(consultaDoProdutoPorId.getPreco());

        List<Produto> listaDeProdutos = produtoDao.buscarTodosProdutos();
        listaDeProdutos.forEach(p -> System.out.println(p.getNome()));

        List<Produto> listaDeProdutosPorNome = produtoDao.buscarPorNomeDoProduto("Xiaomi Redmi");
        listaDeProdutosPorNome.forEach(p -> System.out.println("Busca por nome:" + p.getNome()));

        List<Produto> listaDeProdutosPorNomeDaCategoria = produtoDao.buscarPorNomeDaCategoria("Celulares");
        listaDeProdutosPorNomeDaCategoria.forEach(p -> System.out.println("Busca por nome da categoria:" + p.getNome()));

        BigDecimal buscaDoProdutoPorPreco = produtoDao.buscarPrecoDoProdutoPorNome("Xiaomi Redmi");
        System.out.println("Busca do preço pelo nome do produto:" + buscaDoProdutoPorPreco);

        deletarProduto();
    }

    private static void cadastrarProduto() {
        Categoria celulares = new Categoria("Celulares");

        Produto celular = new Produto("Xiaomi Redmi", "Muito legal", new BigDecimal("800"), celulares);

        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(em);
        CategoriaDao categoriaDao = new CategoriaDao(em);

        em.getTransaction().begin();
        categoriaDao.cadastrar(celulares);
        produtoDao.cadastrar(celular);
        em.getTransaction().commit();
        em.close();
    }

    private static void deletarProduto() {
        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(em);

        Produto produto = produtoDao.buscarPorId(1l);

        em.getTransaction().begin();
        produtoDao.deletar(produto);
        em.flush();
        System.out.println(produtoDao.buscarPorId(1l));
        em.close();
    }

}
