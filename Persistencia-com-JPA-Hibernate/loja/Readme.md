# Introdução à JPA

JPA - Java Persistence API veio para substituir o JDBC

## JDBC e suas desvantagens:

- **Código muito verboso** - Utilizar o JDBC implica em escrever um código de difícil manutenção
- **Alto acomplamento** - JDBC aumenta o acoplamento entre o código da aplicação e o banco de dados

## Hibernate e JPA

> Hibernate é um biblioteca que faz persistência ao BD como uma alternativa ao JDBC

> JPA é uma especificação para ORM(Object Relational Mapping) em Java, com a JPA criou-se um padrão para que não
> ficassemos refém de uma biblioteca

### Diferença entre Hibernate e JPA

- JPA é uma especificação e Hibernate é uma de suas implementações

<image src="image-jpa-implementacoes.png">

**JPA é como se fosse uma interface e bibliotecas como Hibernate implementa os métodos dessa interface.**