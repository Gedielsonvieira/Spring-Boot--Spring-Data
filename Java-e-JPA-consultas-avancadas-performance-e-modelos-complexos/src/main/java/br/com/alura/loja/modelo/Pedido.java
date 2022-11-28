package br.com.alura.loja.modelo;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pedidos")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate data = LocalDate.now();
    private BigDecimal valorTotal;
    @ManyToOne
    private Cliente clienteId;
    @OneToMany(mappedBy = "pedidoId")
    private List<ItemPedido> itens = new ArrayList<>();//Sempre inicializar a lista para não precisar ficar fazendo verificações

    public Pedido(Cliente clienteId) {
        this.clienteId = clienteId;
    }

    public Pedido() {
    }

    public void adicionarItem(ItemPedido item){
        //vinculando os doi lados:
        item.setPedido_id(this);//item conhece o pedido
        this.itens.add(item);//pedido conhece o item
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Cliente getClienteId() {
        return clienteId;
    }

    public void setClienteId(Cliente clienteId) {
        this.clienteId = clienteId;
    }
}
