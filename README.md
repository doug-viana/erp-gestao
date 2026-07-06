Java Enterprise ERP Core
Este projeto é o núcleo de um sistema de ERP (Enterprise Resource Planning) desenvolvido com foco em escalabilidade, segurança e padrões de arquitetura de software de nível corporativo. O objetivo principal foi criar uma API robusta, desacoplada e segura, utilizando as melhores práticas do ecossistema Spring Boot.

Tecnologias e Ferramentas
Java 17+

Spring Boot 3

Spring Security (Autenticação e Autorização)

Spring Data JPA (Persistência e Paginação)

Bean Validation (Validação de dados)

Lombok (Redução de boilerplate)

PostgreSQL (Banco de dados relacional)

Principais diferenciais técnicos
O projeto foi construído priorizando a manutenibilidade e a segurança dos dados:

Arquitetura em Camadas: Separação clara de responsabilidades entre Controllers, Services e Repositories, facilitando a manutenção e testes.

DTO Pattern (Data Transfer Objects): Implementação de DTOs para isolar o modelo de domínio do banco de dados (Entidades) da camada de exibição (API), garantindo maior segurança e flexibilidade no contrato da API.

Global Exception Handling: Centralização do tratamento de erros através de @ControllerAdvice, garantindo que o cliente da API sempre receba respostas estruturadas e amigáveis, eliminando vazamento de stack traces.

Segurança Profissional: Implementação do Spring Security para autenticação básica e controle de acesso a endpoints sensíveis.

Paginação e Performance: Utilização de Pageable para garantir que grandes volumes de dados não degradem a performance da aplicação.

Validação de Dados: Uso de Bean Validation (@Valid, @NotBlank, @Min) para garantir a integridade dos dados antes mesmo de chegarem à camada de serviço.

Estrutura do Projeto
Plaintext
src/main/java/br/com/erp/gestao/
├── config/          # Configurações de Segurança
├── controller/      # Endpoints da API (Camada de Transporte)
├── dto/             # Objetos de Transferência de Dados
├── exception/       # Tratamento global de erros
├── model/           # Entidades do Banco de Dados
├── repository/      # Interface de acesso ao BD
└── service/         # Regras de Negócio
Como executar
Clone o repositório.

Certifique-se de ter o PostgreSQL rodando.

Configure as credenciais do seu banco no application.properties.

Compile e execute a aplicação via Maven ou na sua IDE preferida.

Utilize o Postman ou Insomnia para testar as rotas, lembrando de enviar as credenciais de autenticação no header.