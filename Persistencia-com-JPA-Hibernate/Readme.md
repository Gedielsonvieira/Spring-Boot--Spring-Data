# Introdução à JPA

JPA - Java Persistence API veio para substituir o JDBC porém por baixo dos panos a JPA trabalha com JDBC

## JDBC e suas desvantagens:

- **Código muito verboso** - Utilizar o JDBC implica em escrever um código de difícil manutenção
- **Alto acomplamento** - JDBC aumenta o acoplamento entre o código da aplicação e o banco de dados

## Hibernate e JPA

> Hibernate é um biblioteca que faz persistência ao BD como uma alternativa ao JDBC

> JPA é uma especificação para ORM(Object Relational Mapping) em Java, com a JPA criou-se um padrão para que não
> ficassemos refém de uma biblioteca

- A JPA nada mais é que uma camada de abstração em cima do JDBC, por baixo dos panos a JPA trabalha com JDBC

### Diferença entre Hibernate e JPA

- JPA é uma especificação e Hibernate é uma de suas implementações

<image src="loja/image-jpa-implementacoes.png"></image>

**JPA é como se fosse uma interface e bibliotecas como Hibernate implementa os métodos dessa interface.**

# Configurações e EntityManager

A JPA, precisa de quatro propriedades no arquivo persistence.xml:

- Qual é o driver do banco de dados;
- Onde está a URL de conexão com o banco de dados;
- O usuário e
- Senha.

Feito isso, a JPA consegue gerar as conexões para acessar o nosso banco de dados.

## Mapenado uma Entidade

**Entidade:** É uma classe que faz o mapemaneto de uma tabela no BD, funciona como um espelho de uma tabela no BD.

> Processo/Conceito: Na JPA, precisamos lembrar que a ideia é que ela seja uma especificação para um ORM. Com a ORM nós
> fazemos o mapeamento objeto-relacional. <u>Nós precisamos desse mapeamento, dessa ligação</u> entre o lado da
> orientação
> objetos com o lado do mundo relacional do banco de dados. Isso é feito na classe public class Produto {, e é ela que
> está representando a tabela de produtos, portanto, indicaremos isso para a JPA através da anotação @Entity.

- **@Entity** - Essa anotação indica que essa classe é uma entidade da JPA, ou seja, tem uma tabela no BD à
  representando


- **@Table(name = "...")** - Serve para ensinar para a JPA o nome da Tabela se o nome da entidade for diferente ao da
  tabela. "Eventualmente, se o nome da tabela não for o mesmo da entidade, teremos que ensinar isso para a JPA, porque,
  por padrão, ela considera que o nome da tabela é o mesmo nome da entidade"


- **@Column** - Segue o mesmo conceito da anotação @Table porém servindo de utilidade para o atributo que representa uma
  coluna no BD.


- **@Id** - Serve para informar qual é o atributo que é a chave primária


- **@GeneratedValue(strategy = GenerationType.IDENTITY)** - É utilizada para informar que a geração do valor do
  identificador único da entidade será gerenciada BD.
    - **strategy** - serve para modificar a estratégia de geração da chave primária

## Persistindo uma entidade

> Devemos iniciar e comitar uma transação ao persistir uma entidade ao realizar operações de escrita no banco de dados,
> como insert, update e delete


**EntityManager** - Essa interface funciona como se fosse o gerente, o "manager" das entidades, ou seja, toda vez que
desejarmos acessar o banco de dados, seja para salvar, excluir, atualizar, carregar, fazer um select, ou qualquer outra
operação, nós utilizaremos a interface EntityManager

<h2>Importante ❗</h2>

-[x] Não é uma boa prática mapear enums pela ordem da constante o ideal é mapear pelo nome da constante, assim não
 afetará em nada no BD e para fazer isso utilizamos a anotação com o valor: **@Enumerated(EnumType.STRING)** com isso
 conseguimos salvar no BD o nome da constante.


-[x] Os tipos de atributos que podemos mapear sem a necessidade de configurações adicionais via anotações da JPA são:
    * Tipos primitivos (int, Long, float...);
    * Algumas classes do Java (LocalDate, BigDecimal, String...)

### Relacionamento entre entidades

-[x] Sempre que temos uma entidade, onde o atributo é uma outra entidade, a JPA automaticamente identifica que é um
 relacionamento, e então, precisamos indicar qual é a cardinalidade através de alguma anotação conforme a modelagem do
 BD:
    * @ManyToOne
    * @ManyToMany
    * @OneToMany
    * @OneToOne


-[x] Ao persistir/salvar, temos que ter cuidado, porque, se estamos persistindo com uma entidade e que tem o
 relacionamento com outra entidade, essa outra precisa estar persistida antes, ou receberemos uma
 exception "transiente property value excepetion".

## Ciclo de vida de uma entidade

### Estados no <u>insert</u> da entidade:

- **Transient**: Quando damos "new", isto é, quando instanciamos uma entidade,
  para a JPA, a entidade está em um estado chamado de TRANSIENT. O estado transient é de uma entidade que nunca foi
  persistida, não está gravada no banco de dados, não tem um id e não está sendo gerenciada pela JPA. A JPA desconhece
  essa entidade. É como se fosse um objeto Java puro, que não tem nada a ver com persistência. Esse é o primeiro estado
  de uma entidade. Nesse estado, se alteramos o atributo da entidade ou qualquer outra coisa que façamos com a entidade,
  o EntityManager não está gerenciando, nem verificando, e não vai sincronizar com o banco de dados se nós commitarmos e
  fizermos o close do EntityManager.


- **Managed**: Quando chamamos o método persist(), ela move do estado transient para o estado MANAGED ou gerenciado.
  Managed é o principal estado que uma entidade pode estar, portanto, tudo que acontece com uma entidade nesse estado, a
  JPA observará e poderá sincronizar com o banco de dados, a depender do que fizermos.


- No momento em que fazemos o commit() ou o flush, a JPA pega todas as entidades que estiverem no estado managed e
  sincroniza com o banco de dados. Então, tínhamos uma entidade que era transient, está gerenciada e, depois que
  commitamos, ele perceberá que a entidade não tem id, que ela era transient, ou seja, é necessário fazer um insert dela
  no banco de dados.

### Estados no <u>update</u> da entidade: