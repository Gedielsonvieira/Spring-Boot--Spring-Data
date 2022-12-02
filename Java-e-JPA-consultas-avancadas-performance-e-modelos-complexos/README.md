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

## Consulta com select new session

A JPA tem um recurso chamado select new que serve quando queremos fazer uma consulta, um select de um relatório
e não queremos devolver um array de object. Então na query de consulta escrevemos SELECT new e isso é como se
estivessemos dando new em uma (classe passada depois do new).

> Quando tem um select new, a JPA sabe que é para criar uma instância da classe passada, que não é uma entidade, e ela
> vai
> passar os valores para o construtor. Ela dá um new passando essas informações para um construtor. Para cada registro,
> ela vai criar um objeto e jogar em uma lista.

- Em um select new temos que passar o caminho completo da classe, ou seja, passar o pacote dela

# Performance de consultas

## Entendendo Lazy e Eager

- **Lazy** - O relacionamento **to many**, por padrão, o comportamento é chamado de lazy, que é o carregamento tardio,
  ou seja, temos uma entidade Pedido que tem um relacionamento to many e esse relacionamento só vai ser carregado se
  acessarmos alguma informação dele.


- **Eager** - Todo relacionamento **to one**, o padrão é ele ser eager, ele faz o carregamento antecipado **carregando
  junto com a entidade**, ou seja, mesmo que não acessamos nada desse relacionamento, a JPA vai fazer um join para
  carregar esse relacionamento, **podendo gerar algum problemas de performance ao carregar coisas desnecessárias**.


- Controlamos o carregamento através do parametro **fetch**

### Boas práticas no carregamento de entidades com relacionamentos

- Todo relacionamento **to one deve** ser Lazy, sendo assim o relacionamento só vai ser carregado se fizermos acesso a
  ele.

# Consultas com join Fetch

- Qual o **efeito colateral de tudo ser Lazy**?
  o efeito colateral é que ao acessar alguma informação que é Lazy e o entity manager, que é a nossa ponte com o BD,
  estiver fechado, tomamos a exception **LazyInitializationException**, pois em uma aplicação real é o próprio frameworK
  que está gerenciando o entity manager, **então se precisarmos acessar alguma informação que é Lazy para resolver o
  problema** devemos usar uma **query planejada** que na própria consulta devemos trazer a informação que é Lazy junto
  através do **JOIN FETCH** então nessa consulta planejada é como se o relacionamento Lazy se tornasse Eager sendo
  carregada junto com a entidade principal

### Vantagem join Fetch

- O join fetch permite escolher quais relacionamentos serão carregados em determinada consulta, ao invés de sempre os
  carregar

# Criteria API

## Consultas com parâmetros dinâmicos

> São consultas onde o parametro não se torna obrigatório para realizar a query, se não for informado nenhum argumento
> no
> método a query vai trazer uma informação genérica e se tiver vai ser realizado a query com os devidos filtros.

### Desvantagem da Criteria API

- Código mais complexo de entender e manter

# Outros tópicos

## Simplificando entidades com Embeddable

- **@Embeddable** - Informa para a JPA que a classe é embutivel, ou seja, conseguimos embutir ela dentro de uma
  entidade (para que a JPA considere que esses atributos que não estão diretamente na entidade mesmo assim faça parte da
  tabela da entidade)


- **@Embedded** - Informa para a JPA que ela embuta os atributos da classe que esta na entidade.

> Então temos outra classe com os atributos que antes eram da entidade e mesmo assim conseguimos que os atributos ainda
> façam parte da entidade, deixando o código mais coeso e mais organizado seguindo o princípio da responsabilidade
> única.

## Mapeamento de herança

> A JPA, ela tem suporte para algumas estratégias para o mapeamento de herança as duas principais são:
> - Single Table e
> - Joined

### Single Table

> Nesta estratégia, por mais que no código temos classes e subclasses, no banco de dados os atributos da classe e da
> subclasse vão estar
> presente soemnte em uma tabela.

**@Inheritance** - Informa para a JPA que a classe quem tem essa anotação (deve ser colocada na classe pai) vai ser
utilizada como herança e passamos a estratégia como single table **(strategy = InheritanceType.SINGLE_TABLE)**

#### Vantagem e Desvantagen

✅ Mais performático - Já que está tudo em uma tabela a JPA não realiza joins ao realizar uma query<br>
❌ Desorganização - Fica tudo misturado em uma mesma tabela

## Joined

> Estratégia onde temos uma tabela por classe, ou seja, vamos ter a tabela para a classe pai e uma tabela para cada
> classe filha

**@Inheritance** - Informa para a JPA que a classe quem tem essa anotação (deve ser colocada na classe pai) vai ser
utilizada como herança e passamos a estratégia como joined **(strategy = InheritanceType.JOINED)**

#### Vantagem e Desvantagen

✅ Organização - Cada classe tem sua tabela<br>
❌ Menos performático - Já que cada classe tem sua tabela ao fazer uma query a JPA vai realizar join

## Mapeamento de chaves compostas

> As vezes, tem alguns sistemas, que precisamos mapear uma chave composta, a chave não é um único atributo, são duas ou
> mais informações. E a JPA, tem suporte para isso.

- @EmbeddedId - Essa anotação é inserida no atributo da Entidade e informa para a JPA que aqui dentro estão os atributos que formam a chave primária composta.

### ❗ importante 
Geralmente, classes que têm @Embeddable, é comum colocarmos um implements Serializable para evitar erros