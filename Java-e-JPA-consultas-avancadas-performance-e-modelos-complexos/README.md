# Mais relacionamentos

## Mapeando novas entidades

<image src="./mais-relacionamentos.png"></image>

> **Relacionamento de pedidos para clientes (isso de dá porque cliente_id está dentro de pedidos)**
> Aqui nós temos um relacionamento de muitos para um (@ManyToOne), onde um pedido está vinculado a um cliente e um
> cliente
> pode ter vários pedidos.

## Relacionamento many_to_many simples

<image src="./many-to-many.png"></image>

## Relacionamento bidirecional

<image src="./tabela-itens-pedidos-bidirecional.png"></image>
> "itens_pedidos" - Quando tivermos mais colunas além das que fazem join entre as tabelas devemos criar uma entidade
> para representar a tabela na nossa aplicação.

## ❗ Importante

> Em um relacionamento **Bidirecional (os dois lados estão se mapeando)** devemos indicar isso para a JPA, porque, por
> padrão, se não indicarmos que esse
> é um relacionamento bidirecional, a JPA, ela não vai entender e ela vai supor que isso é um novo mapeamento criando
> uma outra tabela de join.
> Para indicar o relacionamento bidirecional para a JPA utilizamos o mappedBy informando qual o atributo que já está
> fazendo o mapeamento

# Consultas avançadas

## Consultas com funções de agregação

> Consultas onde utilizamos funções para fazer um somatório, um valor mínimo, um valor max, uma média...