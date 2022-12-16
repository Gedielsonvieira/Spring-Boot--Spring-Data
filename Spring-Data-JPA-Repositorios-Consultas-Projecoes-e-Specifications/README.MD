# Spring Data JPA

> É um framework criado para ajudar desenvolvedores Java na sua integração com o banco de dados.

## Diagramas do projeto

<image src="./spring-data/desafio.png">

### Entidades

<image src="./spring-data/Entidades.png">

## Primeiro Repositório

### Sobre o ambiente / Configuração do ambiente

- JDK 8 ou mais recente
- IDE intellij
- Banco MariaDB (Banco de dados para que nossa aplicação possa se comunicar com ele)
- Cliente Banco (Precisamos de um manager para visualizar as informações que foram inseridas no banco de dados.
  Utilizamos o DBeaver)

### Criando a aplicação

Projeto gerado através do <a href="https://start.spring.io/">spring initializr</a> com a dependência do Spring Data JPA

### Configurando o Banco de Dados

Temos que informar para o Spring qual é o BD porque ele sabe que queremos nos comunicar com o BD,
mas como temos um leque de opções de BD que podemos conectar, então temos que baixar o Framework
correspondente ao BD que vamos utilizar.

### Arquitetura do Spring Data

<image src="./spring-data/spring-data.png">

> Dentro do Spring Data nós temos um Driver, temos um JDBC, um Hibernate e um JPA. Conforme aulas anteriores, vimos
> esses componentes fora desse ecossistema Spring Data.<br>
> O bom do Spring Data é que ele acopla todos esses componentes, todas essas coisas tudo dentro de um componente só.<br>
> Então nós já temos todas essas ferramentas prontas para trabalhar. Por exemplo, se caso você já tenha trabalhado com
> JPA
> alguma vez, você sabe que para você conseguir executar alguns comandos em banco de dados, você vai precisa criar um
> EntityManagerFactory, pegar esse EntityManagerFactory para criar um EntityManager e aí você vai conseguir fazer ações
> no
> banco de dados.<br>
> No caso aqui vai ser a mesma coisa, só que não vamos precisar implementar isso em código. O próprio Spring Data já
> implementa isso para nós. Então por isso que fica muito mais limpo, muito mais fácil utilizar o Spring Data na
> aplicação.

### Gerando o Banco de Dados

> Para gerar um BD basta criar uma classe/entidade com seus atributos e seus acessores e modificadores, colocar algumas
> anotações como @Entity, @Id e o Spring Data já gera essa tabela no nosso banco de dados.

ORM - mapeamento objeto relacional (ligação entre o lado da orientação objetos com o lado do mundo relacional do banco
de dados)

### Usando CrudRepository

> Precisamos utilizar o Repository para manipular o banco de dados.<br><br>
> Mesmo que estejamos extendendo o CrudRepository o CrudRepository por sua vez, extende de Repository, então
> indiretamente vamos estar utilizando o Repository.

<image src="./spring-data/tipos-de-repositorios.png">

#### Benefícios do CrudRepository

✅ Realizar métodos CRUD sem a necessidade de criar os objetos do JPA.

#### Importante ❗

Todos os Repositorys do Spring, eles precisam que sejam passados para eles dois parâmetros para que eles funcionem:

* **Primeiro parâmetro** - é qual é o tipo do objeto que você quer que seja criado um repositor dele, que você quer
  manipular ele a base de dados.
* **Segundo parâmetro** - É qual é o tipo do Id

### Inserindo valores

Precisamos pegar o repository e utilizar ele no SpringdataApplication para inserir valores no BD mas
não podemos dar new/instanciar uma interface, é aí que entra a injeção de dependência para que possamos salvar um
determinado registro em outra classe

> Dependência alguma classe/algum pedaço de código que precisa de outro para funcionar

<image src="./spring-data/injecao-de-dependencia.png">

> SpringDataApplication precisa utilizar uma classe externa do seu escopo, que é a CargoRepository.
> E para isso nós precisamos de uma instância de CargoRepository dentro da SpringDataApplication. Então nós vamos enviar
> essa instância através de injeção de dependência.

## Operações CRUD

### Atualizando registro

No framework do Spring Data, para inserir ou atualizar usamos o método save.
O método save serve para entidades transient e detached

## Derived Queries, JPQL e Native Queries

* **Derived Queries -** Através de um comando, de um método escrito em Java, o próprio Spring Data consegue pegar método
  e criar uma Query para o seu banco de dados específico sem termos que criar nenhum comando SQL

### Diferença entre Derived Query, JPQL e Native Query

* **Derived Queries -** queries criadas através de código Java
    * **Vantagem:** Spring Data cria uma Query para o seu banco de dados automaticamente
    * **Desvantagem:** Passa pela fase de transformação em query nativa.<br><br>

* **JPQL -** queries criadas através de uma estrutura SQL, porém com os nomes das entidades Java
    * **Vantagem:** Roda em diversos bancos sem alteração
    * **Desvantagem:** Por ter uma camada de abstração, em algum momento o código jpql tem que ser transformado em
      código nativo, isso gera uma **performance menor**<br><br>

* **Native Query -** queries padrões SQL que conseguimos executar no nosso Client SQL.
    * **Vantagem:** Podem fazer uso de recursos específicos do banco de dados, não passam por nenhuma conversão para
      serem geradas (mais performática).
    * **Desvantagem:** Está diretamente ligada a um banco especifico (é necessário refaze-la em caso de uma mudança de
      banco) e sair bastante do paradigma orientado a objetos

## Paginação e Ordenação

> Com a paginação conseguimos dividir uma consulta para que possamos apresentar páginas menores de um todo. Assim as
> consultas ficam mais rápidas.

* Para paginar uma consulta, devemos enviar um objeto do tipo Pageable como parâmetro para o método findAll, pois no
  objeto pageable encapsulamos a página, a quantidade de itens e qual o tipo de ordenação

* PagingAndSortingRepository - repositório específico para paginação
  <br>

> Com a ordenação trazemos os dados da consulta de forma ordenada através do atributo da entidade em que faz mais
> sentido para o negócio.

## Trabalhando com projeções

> As projeções servem para que, após consultar somente os atributos que desejamos, consigamos retornar só o que foi
> consultado sem termos que precisar consultar tudo e somente mostrar o necessário. São classes ou interfaces com o
> objetivo de encapsular os valores de retorno da consulta dentro dos métodos.

* Projeção baseada na interface, serve para que o Spring Data consiga retornar ao meu serviço de relatórios uma entidade
  que só contenha atributos que desejamos mostrar, ou seja, vai ser consultado e retornado somente os atributos
  desejados


* Podemos também usar uma classe com o mesmo propósito, porém uma classe dá muito mais trabalho de escrever e manter,
  mas pode ter uma vantagem, pois podemos adicionar métodos mais específicos que podem fazer sentido para a view (por
  exemplo, os de formatação).

## Consultas Dinâmicas - Specification

> Quando criamos consultas dinâmicas, utilizamos a Specification e o seu papel na criação das consultas dinâmicas é ter
> um objeto com todos os itens necessários para realizar uma consulta dinâmica, como por exemplo root, criteriaQuery e
> criteriaBuilder, com o objetivo de entregar, ao desenvolver um objeto pronto, para que ele só tenha que se preocupar
> com qual operação SQL ele deseja executar.

* **JpaSpecificationExecutor -** Essa interface vai ser responsável por executar as Specifications dentro do nosso
  Repository.

## Anotações vistas

- **@SpringBootApplication** - Utilizada para que quando iniciado o framework do spring ele percorra por todas as
  anotações que temos dentro da nossa aplicação para executá-las<br>

* #### Anotação JPA
    - **@Entity** - Sinalizamos para o spring data que a classe que contem essa anotação é uma entidade, ou seja,
      queremos que ela crie uma tabela a partir desta classe
    - **@Id** - Sinaliza para o spring data que o atributo que contem essa anotação é o id
    - **@GeneratedValue** - Provedor de persistencia gera os valores do id automaticamente<br>

* #### Anotação Spring
    - **@Repository** -<br><br>

* #### Anotações de Relacionamento

  **FetchMode**

    * **SELECT =** Os relacionamentos serão retornados por meio de SELECTs isolados, ao invés de JOINs;
    * **JOIN =** Exatamente ao contrário do exemplo anterior;
    * **SUBSELECT =** Os relacionamentos serão retornados por meio de SUBSELECTs;<br><br>

  **FetchType**

    * **EAGER =** (Ganancioso) Busca os relacionamentos sempre, acessando ou não o(s) objeto(s) desse relacionamento;
    * **LAZY =** (Preguiçoso) Busca os relacionamentos se, e somente se, o acesso a esse(s) objeto(s) do relacionamento
      for(em) acessado(s). Lança um LazyInitializationException, caso se tente acessar o relacionamento após o
      EntityManager já ter sido fechado;<br><br>

  **FetchMode vs FetchType**

    * **Não especificar** FetchMode **explicitamente** faz com que o que for definido em FetchType seja obedecido;
    * Especificar **explicitamente** o FetchMode como **JOIN** faz com que definições explicitas de FetchType sejam
      ignoradas e assume FetchType como **EAGER**;
    * Especificar **explicitamente** o FetchMode como **SELECT** ou **SUBSELECT** faz com que FetchType seja
      obedecido;<br><br>

  **JoinTable**

  > Como se trata de um relacionamento muito-para-muitos, há a necessidade de haver uma tabela extra, só para mapear o
  cruzamento de relacionamento entre as linhas das tabelas envolvidas.

    * **name:** o nome dessa tabela extra;
    * **joinColumns:** As chaves primárias das tabelas envolvidas no relacionamento serão usadas como chaves primárias e
      estrangeiras na tabela extra;
    * **JoinColumn:** A chave estrangeira que se refere ao dono do relacionamento (A entidade onde a declaração está
      sendo feita);
    * **inverseJoinColumn:** O mesmo que o exemplo anterior, só que sobre a outra entidade envolvida;
      Um exemplo básico de tabela de relacionamento muito-para-muitos:

      |id_funcionario    |id_unidade|
                                                                                  ----------------|----------
      |1              |        1|
      |1              |            2|
      |2              |          2|
      |3              |            1|

      No exemplo acima, nota-se que:

        * O funcionário 1 está alocado em ambas unidades 1 e 2;
        * O funcionário 2 está alocado apenas na unidade 2;
        * O funcionário 3 também está alocado na unidade 1;
          Percebemos que em um relacionamento muito-para-muitos, não há uma outra forma muito elegante de se resolver
          esse desafio sem essa tabela extra: Senão, ou a tabela unidade precisaria ter tantas colunas quantos
          funcionários na empresa (id_funcionario1, id_funcionario2, etc) - Inviável e inelegante - ou o mesmo com a
          tabela de funcionários, que deveria ter o mesmo número de colunas quanto ao número de unidade (id_unidade1,
          id_unidade2, etc)