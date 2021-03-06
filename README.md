# Desafio Dito - Coletor de Dados (Consumer)

Desafio da Empresa dito relacionado a um coletor de dados que rastrem comportamento de um usuário ao utilizar um e-comerce.
Este repositório contém a API responsável por consumer os eventos vindos de um tópico num broker Apache Kafka e salvar as informações em um cluster Elasticsearch.

## Design da Solução

![Arch](https://i.imgur.com/LuT4eRx.png)

   
Em que:

1. Uma API recebe as informações, valida o formato e publica em uma fila para um cluster Apache Kafka
2. Uma outra API consome estas informações e grava em cluster Elasticsearch. Esta API consumidora também fornece 
um endpoint para buscar os registros filtrados por tipo de evento

## Endpoints

Na [API coletora](https://github.com/lcastrooliveira/dito-producer) de eventos:

    POST /api/v1/events

Usado para endpoint utilizado para publicar um evento. Exemplo de evento:

```json
{
	"event": "buy",
	"timestamp": "2023-09-22T13:57:31.2311892-03:00",
	"revenue": 123.89
}
```
E na API consumidora de eventos:

    GET /api/v1/eventsapi/v1/events?eventName=<searchTerm>&page=<pageIndex>&size=<pageSize>

Usado para buscar as informações de eventos cadastradas (autocomplete). Parâmetros:
1. eventName (obrigatório): termo de busca para o campo `event`. Mínimo 2 caracteres.
2. Page (opcional): Número da página, caso não fornecido assume-se valor 0.
3. Size (opcional): Tamanho da página, caso não fornecido assume-se valor 20.

## Tecnologias utilizadas:

* Java 8 com Spring Boot 2
* Apache Kafka
* Elasticsearch
* Docker

### Referências

* [Dito Producer - API que publica os dados](https://github.com/lcastrooliveira/dito-producer)
* [Dito Core - Biblioteca Auxiliar com DTO (Conteúdo complementar)](https://github.com/lcastrooliveira/dito-core)
* [Intro to Apache Kafka with Spring](https://www.baeldung.com/spring-kafka)
* [Intro to Apache Kafka with Spring](https://www.baeldung.com/spring-kafka)
* [Introduction to Spring Data Elasticsearch](https://www.baeldung.com/spring-data-elasticsearch-tutorial)
* [Fabric 8 Maven Plugin for Docker](https://dmp.fabric8.io/)

### 2020 - Lucas de Castro Oliveira