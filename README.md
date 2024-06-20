# E-commerce

## Visão Geral
Este projeto demonstra uma implementação simples de uma API REST com autenticação JWT (JSON Web Token) usando Spring Boot. A API inclui papéis de usuários e controle de acesso para diferentes endpoints.

## Pré-requisitos
- Java 8 ou superior
- Maven ou Gradle
- IDE (IntelliJ IDEA, Eclipse, etc.)

## Estrutura do Projeto
O projeto está dividido em vários pacotes, cada um com responsabilidades específicas:

- **application**: Contém a classe principal para executar a aplicação Spring Boot.
- **config**: Contém classes de configuração para as configurações de segurança.
- **controller**: Contém controladores REST para manipulação de requisições HTTP.
- **model**: Contém modelos de dados para requisições e respostas.
- **security**: Contém classes utilitárias para operações JWT.
- **service**: Contém classes de serviço para lógica de negócios.

## Componentes Principais

### Ponto de Entrada da Aplicação
**JwtRestApiApplication**: A classe principal que inicializa a aplicação Spring Boot.

### Configuração de Segurança
**SecurityConfig**: Configura as definições de segurança HTTP, detalhes do usuário e codificação de senhas.

### Controladores
**AuthController**: Manipula requisições relacionadas à autenticação.

### Modelos
**LoginRequest**: Representa a carga útil da requisição de login.

### Utilitário de Segurança
**JwtUtil**: Contém métodos para gerar e extrair informações de JWTs.

### Serviços
**AuthService**: Fornece métodos para gerar tokens e extrair nomes de usuário.

## Descrição Detalhada

### Ponto de Entrada da Aplicação
O ponto de entrada da aplicação é a classe JwtRestApiApplication. Ela utiliza a anotação `@SpringBootApplication` para habilitar a configuração automática e a varredura de componentes.

### Configuração de Segurança
A classe SecurityConfig configura o seguinte:

- **Proteção CSRF**: Desativada para simplicidade.
- **Segurança dos Endpoints**: Configura quais endpoints são acessíveis para quais papéis:
  - `/login`, `/register`, `/username/**`, `/user/**`, `/admin/**`, `/gerentedeprodutos/**`, `/vendedor/**` e `/cliente/**` são acessíveis sem autenticação.
  - `/admin/**` é restrito a usuários com o papel "ADMIN".
  - `/gerentedeprodutos/**` é restrito a usuários com o papel "GERENTEDEPRODUTOS".
  - `/vendedor/**` é restrito a usuários com o papel "VENDEDOR".
  - `/cliente/**` é restrito a usuários com o papel "CLIENTE".
  - Todas as outras requisições requerem autenticação.
- Configura um serviço de detalhes do usuário em memória com quatro usuários tendo diferentes papéis e um codificador de senhas.

### Controladores
O **AuthController** fornece os seguintes endpoints:

- **POST /register**: Registra um novo usuário com nome de usuário, senha, e-mail e papel.
- **POST /login**: Autentica um usuário e retorna um token JWT.
- **GET /username/{token}**: Extrai o nome de usuário de um token JWT fornecido.
- **GET /user**: Retorna as informações do usuário autenticado.
- **GET /admin**: Restrito a usuários administradores; retorna informações específicas do administrador.
- **GET /gerentedeprodutos**: Restrito a usuários gerentes de produtos; retorna informações específicas do gerente de produtos.
- **GET /vendedor**: Restrito a usuários vendedores; retorna informações específicas do vendedor.
- **GET /cliente**: Restrito a usuários clientes; retorna informações específicas do cliente.

### Modelos
A classe **LoginRequest** representa a carga útil para a requisição de login, contendo um nome de usuário e uma senha.

### Utilitário de Segurança
A classe **JwtUtil** fornece métodos para:

- Gerar um token JWT para um determinado nome de usuário.
- Extrair o nome de usuário, papel e e-mail de um token JWT fornecido.

### Serviços
A classe **AuthService** atua como intermediária entre o controlador e a classe utilitária. Ela utiliza o JwtUtil para gerar tokens e extrair informações de tokens.

## Uso
1. **Executar a Aplicação**: Use sua IDE ou linha de comando para executar a classe JwtRestApiApplication.
   
2. **Autenticar**: Envie uma requisição POST para `/login` com uma carga JSON contendo `username` e `password`. Exemplo:
   ```json
   {
     "username": "Rodrigo",
     "password": "123456"
   }


![image](https://github.com/RodrigoCamargos/AV2_E-commerce/assets/92878953/e2619032-171e-48f8-8584-b85e3d3e49fe)
