# Introdução à JPA

JPA - Java Persistence API veio para substituir o JDBC

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

<image src="loja/image-jpa-implementacoes.png">

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