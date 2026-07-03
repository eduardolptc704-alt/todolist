# 📝 Todolist API

API REST de lista de tarefas (to-do list) desenvolvida durante o curso **Java com Spring Boot** da [Rocketseat](https://www.rocketseat.com.br/). O projeto implementa cadastro de usuários, autenticação via **Basic Auth**, criptografia de senhas com **BCrypt** e um CRUD de tarefas vinculado a cada usuário autenticado.

## 🚀 Tecnologias

<p align="left">
  <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java" />
  <img src="https://img.shields.io/badge/Spring%20Boot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white" alt="Spring Boot" />
  <img src="https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white" alt="Spring" />
  <img src="https://img.shields.io/badge/Hibernate-59666C?style=for-the-badge&logo=hibernate&logoColor=white" alt="Hibernate" />
  <img src="https://img.shields.io/badge/H2%20Database-1E1E1E?style=for-the-badge&logo=h2&logoColor=white" alt="H2 Database" />
  <img src="https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white" alt="Maven" />
  <img src="https://img.shields.io/badge/Lombok-BC1229?style=for-the-badge&logo=lombok&logoColor=white" alt="Lombok" />
  <img src="https://img.shields.io/badge/JUnit5-25A162?style=for-the-badge&logo=junit5&logoColor=white" alt="JUnit 5" />
</p>

## 📦 Bibliotecas e Frameworks

| Biblioteca / Framework | Função no projeto |
|---|---|
| **Spring Boot** | Framework principal da aplicação (auto-configuração, servidor embutido) |
| **Spring Web (`spring-boot-starter-web`)** | Criação da API REST (`@RestController`, `@RequestMapping`, etc.) |
| **Spring Data JPA (`spring-boot-starter-data-jpa`)** | Persistência de dados e repositórios (`JpaRepository`) |
| **Hibernate** | Implementação JPA usada por baixo dos panos pelo Spring Data |
| **H2 Database** | Banco de dados em memória usado em ambiente de desenvolvimento/testes |
| **Lombok** | Geração automática de getters/setters/construtores (`@Data`) |
| **jakarta.servlet / jakarta.persistence** | Especificações Jakarta EE usadas pelo Spring Boot 3 |
| **at.favre.lib:bcrypt** | Hash e verificação de senhas dos usuários |
| **Logback (`ch.qos.logback`)** | Log da aplicação |
| **JUnit 5 (`spring-boot-starter-test`)** | Testes automatizados |

## 🏗️ Arquitetura do projeto

```
src/main/java/springboot/estudo/todolist
├── TodolistApplication.java          # Classe principal (main)
├── controller/
│   └── MinhaPrimeiraController.java  # Controller de exemplo/estudo
├── errors/
│   └── ExceptionHandlerController.java # Tratamento global de exceções
├── filter/
│   └── FilterTaskAuth.java           # Filtro de autenticação Basic Auth
├── task/
│   ├── TaskController.java           # Endpoints de tarefas (CRUD)
│   ├── TaskModel.java                # Entidade Task
│   └── ITaskRepository.java          # Repositório JPA de Task
├── user/
│   ├── UserController.java           # Endpoint de cadastro de usuário
│   ├── UserModel.java                # Entidade User
│   └── IUserRepository.java          # Repositório JPA de User
└── utils/
    └── Utils.java                    # Utilitário para copiar propriedades não nulas (update parcial)
```

## 🔐 Autenticação

A aplicação **não usa Spring Security**: a autenticação é feita manualmente através de um filtro customizado (`FilterTaskAuth`, que estende `OncePerRequestFilter`), aplicado a todas as rotas que começam com `/tasks/`. O fluxo é:

1. O cliente envia o header `Authorization: Basic <usuario:senha em Base64>`.
2. O filtro decodifica as credenciais.
3. O usuário é buscado no banco pelo `username`.
4. A senha informada é comparada com o hash salvo usando **BCrypt**.
5. Se válida, o `id` do usuário autenticado é anexado à requisição (`idUser`) e o fluxo segue para o controller.

## 📌 Endpoints principais

### Usuários
| Método | Rota | Descrição |
|---|---|---|
| `POST` | `/users/` | Cria um novo usuário (senha é hasheada com BCrypt) |

### Tarefas *(requer Basic Auth)*
| Método | Rota | Descrição |
|---|---|---|
| `POST` | `/tasks/` | Cria uma nova tarefa vinculada ao usuário autenticado |
| `GET` | `/tasks/` | Lista as tarefas do usuário autenticado |
| `PUT` | `/tasks/{id}` | Atualiza uma tarefa (apenas o dono pode alterar) |

## ⚙️ Configuração

O projeto usa banco de dados **H2 em memória**, ideal para estudo e testes locais:

```properties
spring.application.name=todolist
spring.datasource.url=jdbc:h2:mem:todolist
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=admin
spring.datasource.password=admin
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.h2.console.enabled=true

server.port=8081
```

Console do H2 disponível em: `http://localhost:8081/h2-console`

## ▶️ Como executar

```bash
# clonar o repositório
git clone https://github.com/eduardolptc704-alt/todolist.git
cd todolist

# rodar com Maven
./mvnw spring-boot:run
```

A aplicação sobe em `http://localhost:8081`.

## 🎓 Sobre

Projeto desenvolvido para fins de estudo, acompanhando o conteúdo do módulo **Java com Spring Boot** da [Rocketseat](https://www.rocketseat.com.br/), com o objetivo de praticar:

- Criação de APIs REST com Spring Boot
- Mapeamento objeto-relacional com JPA/Hibernate
- Criação de filtros customizados de autenticação
- Tratamento global de exceções com `@ControllerAdvice`
- Boas práticas de atualização parcial de entidades (update com `BeanUtils`)

---

Feito com 💜 durante os estudos de back-end com Java e Spring Boot.
