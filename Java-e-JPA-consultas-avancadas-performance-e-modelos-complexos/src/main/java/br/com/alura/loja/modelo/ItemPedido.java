package br.com.alura.loja.modelo;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "itens_pedido")
public class ItemPedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal precoUnitario;
    private int quantidade;
    @ManyToOne
    private Produto produtoId;
    @ManyToOne
    private Pedido pedidoId;

    public ItemPedido(int quantidade, Produto produtoId, Pedido pedidoId) {
        this.quantidade = quantidade;
        this.produtoId = produtoId;
        this.pedidoId = pedidoId;
        this.precoUnitario = produtoId.getPreco();
    }

    public ItemPedido(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(BigDecimal precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Produto getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Produto produtoId) {
        this.produtoId = produtoId;
    }

    public Pedido getPedido_id() {
        return pedidoId;
    }

    public void setPedido_id(Pedido pedido_id) {
        this.pedidoId = pedido_id;
    }
}