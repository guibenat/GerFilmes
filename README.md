# 🎬 GerFilmes - API de Gerenciamento de Cinema

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white)
![Swagger](https://img.shields.io/badge/Swagger-85EA2D?style=for-the-badge&logo=swagger&logoColor=black)

> Uma API RESTful robusta desenvolvida em **Java** com **Spring Boot** para o gerenciamento completo das operações de um cinema (Filmes, Salas, Sessões, Reservas de Assentos e Autenticação).

## 📋 Sumário
- [Sobre o Projeto](#-sobre-o-projeto)
- [Funcionalidades](#-funcionalidades)
- [Tecnologias Utilizadas](#-tecnologias-utilizadas)
- [Arquitetura e Padrões](#-arquitetura-e-padrões)
- [Como Executar](#-como-executar)
- [Documentação da API](#-documentação-da-api)

---

## 📌 Sobre o Projeto
O **GerFilmes** foi projetado para ser o back-end de um sistema de cinemas, facilitando a gestão por parte dos administradores e a reserva de ingressos por parte dos clientes. O sistema conta com controle de acesso (Security) separando as rotas para usuários comuns e administradores.

## 🚀 Funcionalidades
- **Autenticação e Autorização**: Sistema de login e permissões de acesso (Roles de Admin e Cliente).
- **Gerenciamento de Filmes**: Cadastro, listagem, atualização e remoção de filmes.
- **Gerenciamento de Salas e Assentos**: Controle do mapeamento e capacidade de cada sala.
- **Gerenciamento de Sessões**: Associação de filmes a salas em horários específicos.
- **Sistema de Reservas**: Compra e reserva de assentos específicos para as sessões, evitando duplicidades.
- **Relatórios**: (Acesso restrito para admins).

## 🛠️ Tecnologias Utilizadas
- **Java 17+**
- **Spring Boot** (Web, Data JPA, Security)
- **Banco de Dados**: H2 / PostgreSQL / MySQL (Configurável via `application.properties`)
- **Maven**: Gerenciamento de dependências.
- **Swagger / OpenAPI**: Documentação interativa dos endpoints.
- **Padrão DTO**: Para transferência de dados entre a API e as requisições.

## 📂 Arquitetura e Padrões
O projeto segue o padrão **MVC (Model-View-Controller)** adaptado para APIs REST, organizado em camadas lógicas:
- `Controller`: Recebe as requisições HTTP e expõe a API.
- `Service`: Contém toda a regra de negócios.
- `Repository`: Interface de comunicação com o Banco de Dados.
- `Model`: Entidades mapeadas para o banco de dados.
- `DTO (Data Transfer Object)`: Separação dos dados de Request e Response, garantindo segurança e flexibilidade.
- `Security`: Configurações de filtros, JWT e autorização.

## 💻 Como Executar

1. **Clone o repositório:**
```bash
git clone https://github.com/guibenat/GerFilmes.git
```

2. **Acesse a pasta do projeto:**
```bash
cd GerFilmes
```

3. **Compile e baixe as dependências com o Maven:**
```bash
mvn clean install
```

4. **Execute a aplicação:**
```bash
mvn spring-boot:run
```

A aplicação iniciará na porta padrão `8080`. (Acesse `http://localhost:8080`)

## 📚 Documentação da API
A API está documentada com o **Swagger**. Com a aplicação rodando, acesse no seu navegador:

🔗 `http://localhost:8080/swagger-ui.html`

Lá você poderá testar todos os endpoints, visualizar os schemas de *Request* e *Response* (como `FilmeRequest` e `FilmeResponse`) e testar as permissões.

---
Feito com ☕ por [Guilherme](https://github.com/guibenat)
