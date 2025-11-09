✨ Funcionalidades Principais
O sistema é dividido em três experiências principais:

1. Autenticação e Cadastro
Cadastro: Usuários podem criar uma conta informando Nome, E-mail, Senha e Tipo (Aluno ou Monitor).

Login: Autenticação real baseada em E-mail e Senha. O sistema redireciona o usuário para o portal correto (Aluno ou Monitor).

2. Portal do Aluno (aluno.html)
Feed da Comunidade: Vê um feed com todas as dúvidas postadas por outros alunos e monitores.

Postar Dúvidas: Pode enviar novas dúvidas, selecionando a Matéria em um menu.

Espaço Colaborativo: Pode responder às dúvidas de outros colegas, promovendo a comunidade.

Ver Respostas: Vê as respostas do monitor (destacadas em azul) e de outros alunos em suas próprias dúvidas.

3. Portal do Monitor (monitor.html)
Fila de Dúvidas: O monitor vê um painel focado apenas em dúvidas com status ABERTA ou EM_DISCUSSAO.

Responder e Resolver: O monitor pode postar uma resposta para uma dúvida.

Fluxo Automatizado: Ao enviar uma resposta, a dúvida é automaticamente marcada como "RESOLVIDA" e desaparece da fila de pendências do monitor.

4. Catálogo de Conteúdos (conteudo.html)
Visualização de Materiais: Uma página pública onde qualquer usuário (logado ou não) pode ver materiais de estudo.

Links Externos: Os conteúdos (vídeos, artigos) são apresentados com links para acesso em uma nova aba.

Filtro: Permite filtrar os conteúdos por Matéria (ex: "Matemática", "Física").

🛠️ Tecnologias Utilizadas
Este projeto é uma aplicação Full-Stack dividida em duas partes:

Back-end (API REST)

Java 21

Spring Boot 3.3.1

Spring Data JPA (Hibernate)

Maven para gerenciamento de dependências.

Banco de Dados: MySQL

Front-end (Cliente)

HTML5 (Semântico)

CSS3 (Moderno)

JavaScript (ES6+)

Comunicação com a API via fetch().

🏁 Como Executar o Projeto
Siga os passos abaixo para rodar a aplicação localmente.

Pré-requisitos
Java JDK 21 (ou superior)

Maven

Um servidor MySQL (XAMPP, MySQL Workbench, etc.)

Um navegador web moderno (Chrome, Firefox, Edge).

1. Back-end (Servidor Spring Boot)
Clone o repositório:

Bash

git clone [URL-DO-SEU-REPOSITORIO-GIT]
cd [NOME-DA-PASTA]
Configure o Banco de Dados:

Abra seu servidor MySQL.

Crie um novo banco de dados (schema) chamado AEP.

SQL

CREATE DATABASE AEP;
O projeto está configurado no application.properties para usar o usuário root e senha vazia (padrão do XAMPP). Se o seu for diferente, ajuste o arquivo:

Properties

# src/main/resources/application.properties
spring.datasource.username=root
spring.datasource.password=
Execute o Script SQL (Opcional, mas recomendado):

Para popular o banco com dados iniciais (Matérias, Usuários de teste), execute o script SQL do projeto (data.sql ou o que você criou) no seu MySQL Workbench.

Inicie o Servidor:

Abra o projeto na sua IDE (IntelliJ, Eclipse, VS Code).

Rode a classe principal SistemaAepApplication.java.

O servidor estará rodando em http://localhost:8090.

2. Front-end (Cliente)
Navegue até a pasta:

Encontre a pasta onde estão os arquivos login.html, aluno.html, style.css, etc.

Abra o arquivo:

Não é necessário um servidor web. Basta dar um duplo-clique no arquivo login.html para abri-lo diretamente no seu navegador.

A aplicação está pronta! Você pode se cadastrar, logar e usar o sistema.
