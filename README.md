📑 Documentação da API (Swagger UI)A API possui documentação totalmente interativa gerada pelo Swagger. Com a aplicação em execução local, você pode acessar e testar todos os endpoints através do navegador:🔗 URL Local: http://localhost:8080/swagger-ui/index.htmlExemplos de Endpoints DisponíveisMétodoEndpointDescriçãoGET/api/filmesRetorna a lista de todos os filmes cadastrados.GET/api/filmes/{id}Busca os detalhes de um filme específico pelo ID.POST/api/filmesCadastra um novo filme no sistema.PUT/api/filmes/{id}Atualiza as informações de um filme existente.DELETE/api/filmes/{id}Remove um filme do catálogo.🔧 Como Executar o Projeto LocalmentePré-requisitosAntes de iniciar, certifique-se de ter instalado em sua máquina:Java JDK 17 ou superior.Maven instalado (ou use o wrapper incluso ./mvnw).Uma IDE de sua preferência (IntelliJ IDEA, VS Code, Eclipse).Passo a PassoClone o repositório:Bashgit clone [https://github.com/guibenat/GerFilmes.git](https://github.com/guibenat/GerFilmes.git)
Navegue até o diretório do projeto:Bashcd GerFilmes
Configure as variáveis de ambiente ou banco de dados:Edite o arquivo src/main/resources/application.properties se precisar ajustar as credenciais do banco de dados (seja local ou Azure SQL).Execute a aplicação:Bash./mvnw spring-boot:run
(Caso esteja no Windows, utilize mvnw.cmd spring-boot:run)A API estará disponível em http://localhost:8080.👤 AutorDesenvolvido por Guilherme Benatte 👋 Entre em contato ou acompanhe meus projetos:GitHub: @guibenatLinkedIn: Seu LinkedIn Aqui (substitua pelo seu link!)"""with open("README.md", "w", encoding="utf-8") as f:f.write(readme_content)Seu arquivo README para o projeto **GerFilmes** está pronto
[file-tag: code-generated-file-0-1779496678327247723]

O arquivo `README.md` foi gerado com uma estrutura moderna, limpa e profissional baseada no que vi na sua tela (incluindo as abas abertas do Azure, Swagger UI e a estrutura do repositório). 

Aqui está o conteúdo formatado abaixo para que você possa visualizar ou copiar diretamente para o seu projeto:

```markdown
# 🎬 GerFilmes - API de Gerenciamento de Cinema

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Swagger](https://img.shields.io/badge/-Swagger-%2385EA2D?style=for-the-badge&logo=swagger&logoColor=black)
![Azure](https://img.shields.io/badge/azure-%230072C6.svg?style=for-the-badge&logo=microsoftazure&logoColor=white)

O **GerFilmes** é uma API RESTful desenvolvida em **Java** com o framework **Spring Boot** para simular o ecossistema de um sistema de gerenciamento de cinema. O projeto visa fornecer o controle completo sobre o catálogo de filmes, gerenciamento de salas, sessões de exibição e venda/reserva de ingressos, além de contar com documentação interativa e estar estruturado para implantação em nuvem.

---

## 🚀 Funcionalidades Principais (Roadmap do Projeto)

- [x] **Gerenciamento de Filmes (CRUD):** Cadastro de títulos, sinopse, duração, gênero e classificação indicativa.
- [ ] **Controle de Salas e Assentos:** Organização de salas físicas, capacidades e layouts de assentos.
- [ ] **Sessões de Exibição:** Programação de horários vinculando filmes a salas específicas.
- [ ] **Venda de Ingressos:** Simulação de compra de bilhetes com atualização em tempo real de assentos disponíveis.
- [x] **Documentação Automática:** Interface interativa integrada para testes de endpoints.

---

## 🛠️ Tecnologias Utilizadas

O projeto foi construído utilizando as seguintes ferramentas e tecnologias:

- **Linguagem:** Java (Versão 17+)
- **Framework Principal:** Spring Boot
  - *Spring Web:* Para criação de endpoints RESTful.
  - *Spring Data JPA:* Para persistência de dados e mapeamento objeto-relacional.
- **Banco de Dados:** PostgreSQL / Azure SQL Database (configurado para ambiente produtivo).
- **Documentação da API:** Swagger UI / OpenAPI 3.
- **Gerenciador de Dependências:** Maven.

---

## 📂 Estrutura do Projeto

Abaixo está uma visão geral da organização de pacotes do projeto (arquitetura padrão em camadas):

```text
📁 GerFilmes
└── 📁 Filmes
    ├── 📁 controller    # Camada de exposição dos endpoints REST
    ├── 📁 model         # Entidades de mapeamento do banco de dados (ex: Filme)
    ├── 📁 repository    # Interfaces de comunicação com o banco de dados (JPA)
    └── 📁 service       # Camada de regras de negócio e lógica da aplicação
📑 Documentação da API (Swagger UI)A API possui documentação totalmente interativa gerada pelo Swagger. Com a aplicação em execução local, você pode acessar e testar todos os endpoints através do navegador:🔗 URL Local: http://localhost:8080/swagger-ui/index.htmlExemplos de Endpoints DisponíveisMétodoEndpointDescriçãoGET/api/filmesRetorna a lista de todos os filmes cadastrados.GET/api/filmes/{id}Busca os detalhes de um filme específico pelo ID.POST/api/filmesCadastra um novo filme no sistema.PUT/api/filmes/{id}Atualiza as informações de um filme existente.DELETE/api/filmes/{id}Remove um filme do catálogo.🔧 Como Executar o Projeto LocalmentePré-requisitosAntes de iniciar, certifique-se de ter instalado em sua máquina:Java JDK 17 ou superior.Maven instalado (ou use o wrapper incluso ./mvnw).Uma IDE de sua preferência (IntelliJ IDEA, VS Code, Eclipse).Passo a PassoClone o repositório:Bashgit clone [https://github.com/guibenat/GerFilmes.git](https://github.com/guibenat/GerFilmes.git)
Navegue até o diretório do projeto:Bashcd GerFilmes
Configure as variáveis de ambiente ou banco de dados:Edite o arquivo src/main/resources/application.properties se precisar ajustar as credenciais do banco de dados (seja local ou Azure SQL).Execute a aplicação:Bash./mvnw spring-boot:run
(Caso esteja no Windows, utilize mvnw.cmd spring-boot:run)A API estará disponível em http://localhost:8080.👤 AutorDesenvolvido por Guilherme Benatte 👋 Entre em contato ou acompanhe meus projetos:GitHub: @guibenatLinkedIn: Seu LinkedIn Aqui
### 💡 Dicas de Ajustes:
1. No final do arquivo, lembre-se de alterar o link `Seu LinkedIn Aqui` pela URL real do seu perfil.
2. Como você está na fase inicial (criando a classe de filmes), deixei um modelo de **Roadmap (Funcionalidades Pri
