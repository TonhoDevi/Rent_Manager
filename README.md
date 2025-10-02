# Desafio Gestão de Aluguéis

Este projeto é uma API REST desenvolvida em Spring Boot para gerenciar imóveis, inquilinos e aluguéis. Ele permite o cadastro, consulta, atualização e remoção dessas entidades, além de funcionalidades específicas de vinculação entre elas.

## Funcionalidades Principais

- **Imóveis**
  - Cadastro de imóveis
  - Consulta de imóveis
  - Atualização de imóveis
  - Remoção de imóveis

- **Inquilinos**
  - Cadastro de inquilinos
  - Consulta de inquilinos
  - Atualização de inquilinos
  - Remoção de inquilinos
  - Vinculação de aluguéis existentes ao inquilino (desde que o aluguel esteja vinculado a um imóvel do inquilino)

- **Aluguéis**
  - Cadastro de aluguéis
  - Consulta de aluguéis
  - Atualização de aluguéis
  - Remoção de aluguéis

## Estrutura do Projeto

- `controllers/` - Endpoints REST para cada entidade
- `dtos/` - Objetos de transferência de dados
- `models/` - Entidades JPA
- `repositorys/` - Repositórios Spring Data JPA
- `services/` - Lógica de negócio
- `exceptions/` - Tratamento de erros personalizados
- `utils/` - Configurações auxiliares

## Documentação e Testes Manuais

- A API é documentada automaticamente via Swagger (springdoc-openapi). Após iniciar o projeto, acesse:
  - [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html) ou [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)
  - Use a interface para testar endpoints manualmente.

## Como Executar

1. Instale as dependências com Maven:
   ```
   mvn clean install
   ```
2. Inicie a aplicação:
   ```
   mvn spring-boot:run
   ```
3. Acesse a documentação Swagger conforme instruções acima.

## Testes Unitários

- Os testes unitários estão localizados em `src/test/java/br/com/FucturaBope/`
- Para rodar os testes:
  ```
  mvn test
  ```

## Observações

- O projeto utiliza Lombok para reduzir boilerplate nas entidades. Certifique-se de que seu IDE está com o plugin Lombok habilitado.
- Os relacionamentos entre entidades são feitos via IDs (por exemplo, o aluguel guarda apenas o id do inquilino e do imóvel).
- O tratamento de erros é feito por exceções customizadas e respostas padronizadas.

## Contato

Dúvidas ou sugestões? Entre em contato com o desenvolvedor do projeto.

