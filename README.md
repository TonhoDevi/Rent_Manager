# 🏘️ Rental Management API

> API REST para gestão completa de imóveis, inquilinos e contratos de aluguel.

![Java](https://img.shields.io/badge/Java-17-007396?style=flat-square&logo=java)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.6-6DB33F?style=flat-square&logo=springboot)
![Maven](https://img.shields.io/badge/Maven-3.x-C71A36?style=flat-square&logo=apachemaven)
![H2](https://img.shields.io/badge/Database-H2%20%7C%20MySQL-003B7A?style=flat-square&logo=mysql)
![Swagger](https://img.shields.io/badge/Docs-Swagger%20UI-85EA2D?style=flat-square&logo=swagger)
![License](https://img.shields.io/badge/License-MIT-yellow?style=flat-square)

---

## 📋 Sobre o Projeto

A **Rental Management API** é uma aplicação back-end desenvolvida com **Spring Boot 3** que oferece uma solução completa para o gerenciamento de contratos de aluguel. O sistema permite cadastrar e vincular **imóveis**, **inquilinos** e **aluguéis**, além de fornecer funcionalidades de negócio como controle de inadimplência, histórico de pagamentos e ordenação por valor.

A API é totalmente documentada via **Swagger/OpenAPI**, permitindo exploração e testes interativos diretamente pelo navegador.

---

## 🚀 Tecnologias Utilizadas

| Tecnologia | Versão | Finalidade |
|---|---|---|
| Java | 17 | Linguagem principal |
| Spring Boot | 3.5.6 | Framework web e IoC |
| Spring Data JPA | — | Persistência e repositórios |
| Hibernate | — | ORM e validações |
| H2 Database | — | Banco em memória (dev/test) |
| MySQL | — | Banco relacional (produção) |
| Lombok | 1.18.30 | Redução de boilerplate |
| ModelMapper | 3.2.4 | Mapeamento DTO ↔ Entidade |
| Springdoc OpenAPI | 2.8.4 | Documentação Swagger |
| Maven | 3.x | Gerenciador de dependências |

---

## 📦 Estrutura do Projeto

```
src/main/java/br/com/FucturaBope/
├── controllers/          # Endpoints REST (camada de apresentação)
│   ├── ControllerAluguel.java
│   ├── ControllerImovel.java
│   └── ControllerInquilino.java
├── dtos/                 # Data Transfer Objects
│   ├── DtoAluguel.java
│   ├── DtoImovel.java
│   └── DtoInquilino.java
├── models/               # Entidades JPA (domínio)
│   ├── Aluguel.java
│   ├── Imovel.java
│   └── Inquilino.java
├── repositorys/          # Repositórios Spring Data JPA
│   ├── RepositoryAluguel.java
│   ├── RepositoryImovel.java
│   └── RepositoryInquilino.java
├── services/             # Regras de negócio
│   ├── ServiceAluguel.java
│   ├── ServiceImovel.java
│   └── ServiceInquilino.java
├── exceptions/           # Tratamento global de erros
│   ├── GlobalException.java
│   ├── ObjectNotFoundException.java
│   ├── UnprocessableEntityException.java
│   ├── StandardError.java
│   ├── ValidationError.java
│   └── FieldMessage.java
└── utils/                # Configurações auxiliares
    ├── CorsConfig.java
    ├── ModelMapperConfig.java
    └── Swagger.java
```

---

## ✨ Funcionalidades

### 🏠 Imóveis
- Cadastrar novo imóvel (vinculado a um inquilino)
- Listar todos os imóveis ou filtrar por inquilino
- Consultar imóvel por ID
- Atualizar dados do imóvel
- Remover imóvel

### 👤 Inquilinos
- Cadastrar novo inquilino
- Listar todos os inquilinos ou consultar por ID
- Atualizar dados do inquilino
- Remover inquilino
- Associar imóvel a um inquilino
- Vincular aluguel existente ao inquilino *(somente se o imóvel do aluguel pertencer ao inquilino)*
- Consultar aluguéis vinculados ao inquilino

### 📄 Aluguéis
- Cadastrar novo aluguel
- Listar todos os aluguéis ou consultar por ID
- Atualizar dados do aluguel
- Remover aluguel
- Marcar aluguel como **pago**
- Listar aluguéis **pagos**
- Listar aluguéis **não pagos**
- Listar aluguéis **atrasados**
- Listar aluguéis ordenados por **maior valor**

### ⚠️ Tratamento de Erros
- Exceções customizadas com respostas padronizadas
- `404 Not Found` — Recurso não encontrado
- `422 Unprocessable Entity` — Regra de negócio violada
- `400 Bad Request` — Dados de entrada inválidos com mapeamento de campos

---

## 📡 Endpoints da API

### 🏠 Imóveis — `/imoveis`

| Método | Rota | Descrição |
|---|---|---|
| `GET` | `/imoveis` | Lista todos os imóveis. Parâmetro opcional: `?inquilino={id}` |
| `GET` | `/imoveis/{id}` | Consulta imóvel por ID |
| `POST` | `/imoveis?inquilino={id}` | Cadastra novo imóvel vinculado a um inquilino |
| `PUT` | `/imoveis/{id}?inquilino={id}` | Atualiza imóvel existente |
| `DELETE` | `/imoveis/{id}` | Remove imóvel por ID |

### 👤 Inquilinos — `/inquilinos`

| Método | Rota | Descrição |
|---|---|---|
| `GET` | `/inquilinos` | Lista todos os inquilinos |
| `GET` | `/inquilinos/{id}` | Consulta inquilino por ID |
| `POST` | `/inquilinos` | Cadastra novo inquilino |
| `PUT` | `/inquilinos/{id}` | Atualiza inquilino existente |
| `DELETE` | `/inquilinos/{id}` | Remove inquilino por ID |
| `PUT` | `/inquilinos/{idInquilino}/imoveis/{idImovel}` | Associa imóvel ao inquilino |
| `PUT` | `/inquilinos/{idInquilino}/adicionar-aluguel/{idAluguel}` | Vincula aluguel ao inquilino |

### 📄 Aluguéis — `/alugueis`

| Método | Rota | Descrição |
|---|---|---|
| `GET` | `/alugueis` | Lista todos os aluguéis |
| `GET` | `/alugueis/{id}` | Consulta aluguel por ID |
| `POST` | `/alugueis` | Cadastra novo aluguel |
| `PUT` | `/alugueis/{id}` | Atualiza aluguel existente |
| `DELETE` | `/alugueis/{id}` | Remove aluguel por ID |
| `PUT` | `/alugueis/{id}/pagar` | Marca aluguel como pago |
| `GET` | `/alugueis/pagos` | Lista aluguéis pagos |
| `GET` | `/alugueis/nao-pagos` | Lista aluguéis não pagos |
| `GET` | `/alugueis/atrasados` | Lista aluguéis com pagamento atrasado |
| `GET` | `/alugueis/maior-valor` | Lista aluguéis em ordem decrescente de valor |

---

## ⚙️ Como Executar

### Pré-requisitos

- [Java 17+](https://adoptium.net/)
- [Maven 3.x](https://maven.apache.org/)
- (Opcional) [MySQL](https://www.mysql.com/) para ambiente de produção

### Passos

**1. Clone o repositório:**
```bash
git clone https://github.com/TonhoDevi/rental-management-api.git
cd rental-management-api
```

**2. Instale as dependências:**
```bash
mvn clean install
```

**3. Inicie a aplicação:**
```bash
mvn spring-boot:run
```

**4. Acesse a documentação interativa:**

- Swagger UI: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
- OpenAPI JSON: [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs)
- Console H2: [http://localhost:8080/h2-console](http://localhost:8080/h2-console)

> **Credenciais H2:** `URL: jdbc:h2:~/testdb` | `User: sa` | `Password: Admin123`

---

## 🧪 Testes

Os testes unitários estão localizados em `src/test/java/br/com/FucturaBope/`.

Para executar todos os testes:
```bash
mvn test
```

---

## 🗄️ Banco de Dados

O projeto está configurado para usar **H2 em memória** por padrão (ideal para desenvolvimento e testes).

Para usar **MySQL em produção**, edite `src/main/resources/application.properties` e substitua as configurações do datasource:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/rental_db
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect
spring.jpa.hibernate.ddl-auto=update
```

---

## 📝 Observações

- O projeto utiliza **Lombok** — certifique-se de que seu IDE possui o plugin Lombok habilitado (IntelliJ IDEA: `Settings > Plugins > Lombok`).
- O encoding padrão do projeto é **UTF-8**.
- **Java mínimo recomendado:** 17.
- O padrão de projeto segue a arquitetura em camadas: **Controller → Service → Repository**.

---

## 📄 Licença

Este projeto está sob a licença **MIT**. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.

---

<p align="center">
  Desenvolvido com ☕ e Spring Boot
</p>
