# library-api

API REST de gerenciamento de biblioteca desenvolvida com Spring Boot, como parte do curso da Udemy **"Spring Boot Expert: JPA, REST, JWT, OAuth2 com Docker e AWS"**.

## 🚀 Tecnologias utilizadas
- Java 21
- Spring Boot 3.3.1
- Spring Data JPA
- PostgreSQL
- Docker
- Maven
- Lombok
- JUnit (testes)

## 📦 Dependências principais
- spring-boot-starter-data-jpa
- postgresql (runtime)
- lombok
- spring-boot-starter-test

## 📚 Funcionalidades
- Cadastro e gerenciamento de livros e autores
- Relacionamento entre entidades (Livro ↔ Autor)
- Operações de CRUD
- Consultas com Query Methods e JPQL
- Controle transacional com `@Transactional`

## 🧱 Estrutura do projeto
- Camada principal (`main`) com regras de negócio e persistência
- Camada de testes (`test`) para validação das funcionalidades

## 🗂️ Modelagem
- Entidades:
    - Livro
    - Autor
- Enum:
    - GeneroLivro

## 🧠 Conceitos aplicados
- Injeção de dependência (`@Autowired`)
- Camadas com `@Service` e `@Repository`
- Queries com JPQL
- Gerenciamento de transações
- Estados das entidades no JPA:
    - Transient
    - Managed
    - Detached
    - Removed

## 🛢️ Banco de dados
- PostgreSQL
- Integração com Spring Data JPA

## 🐳 Uso do Docker (opcional)

O Docker foi utilizado apenas para provisionar o banco de dados PostgreSQL e a ferramenta PgAdmin durante o desenvolvimento.  
A aplicação não está containerizada.

### 🔧 Criando a rede (caso não exista)
```bash
docker network create library-network