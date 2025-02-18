# Desafio itaú

Este projeto é parte de um desafio proposto pelo Itaú, disponível em [desafio-itau-vaga-99-junior](https://github.com/rafaellins-itau/desafio-itau-vaga-99-junior/tree/main).

## Descrição

A aplicação Itau Teste é uma API RESTful desenvolvida com Spring Boot que gerencia transações financeiras. Ela permite criar, deletar e obter estatísticas de transações realizadas nos últimos segundos.

## Funcionalidades

- **Criar Transação**: Adiciona uma nova transação.
- **Deletar Transações**: Remove todas as transações.
- **Obter Estatísticas**: Retorna estatísticas das transações realizadas nos últimos segundos.

## Estrutura do Projeto

- **Controllers**: [TransactionController](src/main/java/luiz/itauteste/controllers/TransactionController.java)
- **Services**: [TransactionService](src/main/java/luiz/itauteste/services/TransactionService.java)
- **DTOs**: [CreateTransactionDTO](src/main/java/luiz/itauteste/dtos/CreateTransactionDTO.java), [StatsTransactionDTO](src/main/java/luiz/itauteste/dtos/StatsTransactionDTO.java)
- **Entities**: [Transaction](src/main/java/luiz/itauteste/entities/Transaction.java)
- **Tests**: [TransactionControllerTest](src/test/java/luiz/itauteste/controllers/TransactionControllerTest.java), [TransactionServiceTest](src/test/java/luiz/itauteste/services/TransactionServiceTest.java), [ItauTesteApplicationTests](src/test/java/luiz/itauteste/ItauTesteApplicationTests.java)

## Requisitos

- Java 21
- Maven

## Como Executar

1. Clone o repositório:
   ```sh
   git clone https://github.com/seu-usuario/itau-teste.git
   
2. Navegue até o diretio do projeto:
    ```sh
   cd itau-teste
   
3. Execute o projeto:
    ```sh
    ./mvnw test   