# Mais relacionamentos

## Mapeando novas entidades

<image src="./mais-relacionamentos.png"></image>

> **Relacionamento de pedidos para clientes (isso de dá porque cliente_id está dentro de pedidos)**
> Aqui nós temos um relacionamento de muitos para um (@ManyToOne), onde um pedido está vinculado a um cliente e um
> cliente
> pode ter vários pedidos.

## Relacionamento many_to_many

<image src="./tabela-itens-pedidos.png"></image>
> **Quando temos um relacionamento muitos para muitos (1 produto pode estar em muitos pedidos e 1 pedido pode ter muitos
produtos) é necessário ter uma tabela intermediária para criar esse relacionamento.**