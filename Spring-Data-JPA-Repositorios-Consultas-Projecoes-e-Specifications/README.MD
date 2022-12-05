# Spring Data JPA

> É um framework criado para ajudar desenvolvedores Java na sua integração com o banco de dados.

## Sobre o ambiente / Configuração do ambiente

- JDK 8 ou mais recente
- IDE intellij
- Banco MariaDB (Banco de dados para que nossa aplicação possa se comunicar com ele)
- Cliente Banco (Precisamos de um manager para visualizar as informações que foram inseridas no banco de dados.
  Utilizamos o DBeaver)

## Criando a aplicação

Projeto gerado através do <a href="https://start.spring.io/">spring initializr</a> com a dependência do Spring Data JPA

## Configurando o Banco de Dados

Temos que informar para o Spring qual é o BD porque ele sabe que queremos nos comunicar com o BD,
mas como temos um leque de opções de BD que podemos conectar, então temos que baixar o Framework
correspondente ao BD que vamos utilizar.

## Anotações vistas

- **@SpringBootApplication** - Utilizada para que quando iniciado o framework do spring ele percorra por todas as
  anotações que
  temos dentro da nossa aplicação para executá-las