# Desafio Gestão de Aluguéis

Este projeto é uma API REST desenvolvida em Spring Boot para gerenciar imóveis, inquilinos e aluguéis. Permite cadastro, consulta, atualização, remoção e vinculação entre entidades, além de funcionalidades específicas de negócio.

## Funcionalidades Detalhadas

### Imóveis
- Cadastro de imóveis
- Consulta de todos os imóveis ou por ID
- Consulta de imóveis por inquilino
- Atualização de imóveis
- Remoção de imóveis

### Inquilinos
- Cadastro de inquilinos
- Consulta de todos os inquilinos ou por ID
- Atualização de inquilinos
- Remoção de inquilinos
- Vinculação de imóveis ao inquilino
- Vinculação de aluguéis existentes ao inquilino (apenas se o aluguel estiver vinculado a um imóvel do inquilino)
- Consulta dos aluguéis vinculados ao inquilino

### Aluguéis
- Cadastro de aluguéis
- Consulta de todos os aluguéis ou por ID
- Atualização de aluguéis
- Remoção de aluguéis
- Consulta de aluguéis atrasados
- Consulta de aluguéis pagos
- Consulta de aluguéis não pagos
- Consulta de aluguéis por maior valor
- Marcar aluguel como pago

### Tratamento de Erros
- Erros tratados por exceções customizadas e respostas padronizadas (ex: 404 para não encontrado, 422 para dados inválidos).

## Estrutura do Projeto
- `controllers/` - Endpoints REST para cada entidade
- `dtos/` - Objetos de transferência de dados
- `models/` - Entidades JPA
- `repositorys/` - Repositórios Spring Data JPA
- `services/` - Lógica de negócio
- `exceptions/` - Tratamento de erros personalizados
- `utils/` - Configurações auxiliares

## Documentação e Testes Manuais
- A API é documentada automaticamente via Swagger (springdoc-openapi).
- Após iniciar o projeto, acesse:
  - [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html) ou [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)
  - Use a interface para testar endpoints manualmente.

## Endpoints

Abaixo estão todos os endpoints disponíveis na API, organizados por entidade e com explicação detalhada.

## Imóveis (`/imoveis`)
- `GET /imoveis` — Lista todos os imóveis. Parâmetro opcional: `inquilino` para filtrar imóveis de um inquilino.
- `GET /imoveis/{id}` — Consulta imóvel por ID.
- `POST /imoveis?inquilino={id}` — Cadastra novo imóvel vinculado a um inquilino. Body: DtoImovel.
- `PUT /imoveis/{id}?inquilino={id}` — Atualiza imóvel existente vinculado a um inquilino. Body: DtoImovel.
- `DELETE /imoveis/{id}` — Remove imóvel por ID.

## Inquilinos (`/inquilinos`)
- `GET /inquilinos` — Lista todos os inquilinos.
- `GET /inquilinos/{id}` — Consulta inquilino por ID.
- `POST /inquilinos` — Cadastra novo inquilino. Body: DtoInquilino.
- `PUT /inquilinos/{id}` — Atualiza inquilino existente. Body: DtoInquilino.
- `DELETE /inquilinos/{id}` — Remove inquilino por ID.
- `PUT /inquilinos/{idInquilino}/imoveis/{idImovel}` — Associa imóvel ao inquilino.
- `PUT /inquilinos/{idInquilino}/adicionar-aluguel/{idAluguel}` — Vincula aluguel existente ao inquilino (se o imóvel do aluguel pertencer ao inquilino).

## Aluguéis (`/alugueis`)
- `GET /alugueis` — Lista todos os aluguéis.
- `GET /alugueis/{id}` — Consulta aluguel por ID.
- `POST /alugueis` — Cadastra novo aluguel. Body: DtoAluguel.
- `PUT /alugueis/{id}` — Atualiza aluguel existente. Body: DtoAluguel.
- `DELETE /alugueis/{id}` — Remove aluguel por ID.
- `GET /alugueis/maior-valor` — Lista aluguéis ordenados por valor decrescente.
- `GET /alugueis/atrasados` — Lista aluguéis atrasados (baseado em `diasAtrasados`).
- `PUT /alugueis/{id}/pagar` — Marca aluguel como pago.
- `GET /alugueis/pagos` — Lista aluguéis pagos.
- `GET /alugueis/nao-pagos` — Lista aluguéis não pagos.

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
- O encoding padrão é UTF-8. O Java mínimo recomendado é 17.
- Para dúvidas ou sugestões, entre em contato com o desenvolvedor do projeto.
