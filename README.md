Workshop Spring Boot & JPA: API de E-commerce
Este projeto é uma API RESTful completa para um sistema de e-commerce simples, desenvolvido como parte de um estudo aprofundado do ecossistema Spring. A aplicação foi construída com base no modelo de domínio do workshop "Web Services com Spring Boot e JPA/Hibernate" do professor Nélio Alves (DevSuperior), mas implementada com práticas modernas de desenvolvimento, incluindo uma arquitetura em camadas bem definida, o padrão DTO (Data Transfer Object) e mapeamento automático com MapStruct.

Funcionalidades Principais
Arquitetura em Camadas: O projeto é claramente dividido nas camadas de Controller, Service, Repository e Domain (Entities, DTOs, Mappers).

CRUD Completo: Endpoints RESTful para todas as operações de CRUD (Create, Read, Update, Delete) para as principais entidades: Utilizadores, Produtos, Categorias e Pedidos.

Padrão DTO: Utilização de DTOs de Requisição (Request) e Resposta (Response) para garantir um "contrato" seguro e flexível para a API, desacoplando o modelo de domínio da camada de apresentação.

Mapeamento Automático: Implementação da camada de Mappers com MapStruct para automatizar a conversão entre DTOs e Entidades de forma limpa e performática.

Tratamento de Exceções: Implementação de um ControllerAdvice para capturar exceções de negócio (como RecursoNaoEncontrado) e retornar respostas de erro padronizadas para o cliente.

Tecnologias Utilizadas
Java 21

Spring Boot 3

Spring Data JPA & Hibernate

MySQL (Banco de Dados)

Maven (Gestor de Dependências)

MapStruct (Mapeamento de Objetos)
