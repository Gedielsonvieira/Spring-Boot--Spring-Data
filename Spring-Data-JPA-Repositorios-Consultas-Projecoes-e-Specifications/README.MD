# Spring Data JPA

> É um framework criado para ajudar desenvolvedores Java na sua integração com o banco de dados.

## Diagramas do projeto
<image src="./spring-data/desafio.png">

### Entidades
<image src="./spring-data/entidades.png">

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

> Dentro do Spring Data nós temos um Driver, temos um JDBC, um Hibernate e um JPA. Conforme aulas anteriores, vimos esses componentes fora desse ecossistema Spring Data.<br>
O bom do Spring Data é que ele acopla todos esses componentes, todas essas coisas tudo dentro de um componente só.<br>
Então nós já temos todas essas ferramentas prontas para trabalhar. Por exemplo, se caso você já tenha trabalhado com JPA alguma vez, você sabe que para você conseguir executar alguns comandos em banco de dados, você vai precisa criar um EntityManagerFactory, pegar esse EntityManagerFactory para criar um EntityManager e aí você vai conseguir fazer ações no banco de dados.<br>
No caso aqui vai ser a mesma coisa, só que não vamos precisar implementar isso em código. O próprio Spring Data já implementa isso para nós. Então por isso que fica muito mais limpo, muito mais fácil utilizar o Spring Data na aplicação.

### Gerando o Banco de Dados

> Para gerar um BD basta criar uma classe/entidade com seus atributos e seus acessores e modificadores, colocar algumas anotações como @Entity, @Id e o Spring Data já gera essa tabela no nosso banco de dados.

ORM - mapeamento objeto relacional (ligação entre o lado da orientação objetos com o lado do mundo relacional do banco de dados)

### Usando CrudRepository
> Precisamos utilizar o Repository para manipular o banco de dados.<br><br>
> Mesmo que estejamos extendendo o CrudRepository o CrudRepository por sua vez, extende de Repository, então indiretamente vamos estar utilizando o Repository.

<image src="./spring-data/tipos-de-repositorios.png">

#### Benefícios do CrudRepository
✅ Realizar métodos CRUD sem a necessidade de criar os objetos do JPA.

#### Importante ❗
Todos os Repositorys do Spring, eles precisam que sejam passados para eles dois parâmetros para que eles funcionem:
  * **Primeiro parâmetro** - é qual é o tipo do objeto que você quer que seja criado um repositor dele, que você quer manipular ele a base de dados.
  * **Segundo parâmetro** - É qual é o tipo do Id


### Inserindo valores
Precisamos pegar o repository e utilizar ele no SpringdataApplication para inserir valores no BD mas
não podemos dar new/instanciar uma interface, é aí que entra a injeção de dependência para que possamos salvar um determinado registro em outra classe

> Dependência alguma classe/algum pedaço de código que precisa de outro para funcionar

<image src="./spring-data/injecao-de-dependencia.png">

> SpringDataApplication precisa utilizar uma classe externa do seu escopo, que é a CargoRepository.
> E para isso nós precisamos de uma instância de CargoRepository dentro da SpringDataApplication. Então nós vamos enviar essa instância através de injeção de dependência.

## Operações CRUD

### Atualizando registro
No framework do Spring Data, para inserir ou atualizar usamos o método save.
O método save serve para entidades transient e detached


### Anotações vistas

- **@SpringBootApplication** - Utilizada para que quando iniciado o framework do spring ele percorra por todas as
  anotações que
  temos dentro da nossa aplicação para executá-las

* #### Anotação JPA
  - **@Entity** - Sinalizamos para o spring data que a classe que contem essa anotação é uma entidade, ou seja, queremos que ela crie uma tabela a partir desta classe 
  - **@Id** - Sinaliza para o spring data que o atributo que contem essa anotação é o id
  - **@GeneratedValue** - Provedor de persistencia gera os valores do id automaticamente

* #### Anotação Spring
  - **@Repository** -