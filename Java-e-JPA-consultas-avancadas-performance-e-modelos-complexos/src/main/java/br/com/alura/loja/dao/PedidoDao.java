package br.com.alura.loja.dao;

import javax.persistence.EntityManager;

import br.com.alura.loja.modelo.Pedido;
import br.com.alura.loja.vo.RelatorioDeVendasVo;

import java.math.BigDecimal;
import java.util.List;

public class PedidoDao {

    private EntityManager em;

    public PedidoDao(EntityManager em) {
        this.em = em;
    }

    public void cadastrar(Pedido pedido) {
        this.em.persist(pedido);
    }

    public BigDecimal valorTotalVendido() {
        //Pedido - entidade
        //valorTotal - atributo no qual queremos buscar a informações
        return em.createQuery("SELECT SUM(valorTotal) FROM Pedido", BigDecimal.class)
                .getSingleResult();
    }

    public List<RelatorioDeVendasVo> relatorioDeVendas() {
        String jpql = "SELECT new br.com.alura.loja.vo.RelatorioDeVendasVo("
                + "prod.nome, "
                + "SUM(item.quantidade), "
                + "MAX(ped.data)) "
                + "FROM Pedido ped "
                + "JOIN ped.itens item "
                + "JOIN item.produto prod "
                + "GROUP BY prod.nome "
                + "ORDER BY item.quantidade DESC";
        return em.createQuery(jpql, RelatorioDeVendasVo.class)
                .getResultList();
    }

    public Pedido buscarPedidoComCLiente(Long id){
        //Para carregar um relacionamento Lazy junto com Pedido utilizamos o JOIN FETCH com o nome do atributo
        return em.createQuery("SELECT p FROM Pedido p JOIN FETCH p.cliente WHERE p.id = :id", Pedido.class)
                .setParameter("id",id)
                .getSingleResult();
    }

}
