# Changelog

Todas as mudanças notáveis na API do **Agora System** serão documentadas neste arquivo.

O formato é baseado no [Keep a Changelog](https://keepachangelog.com/pt-BR/1.1.0/).

---

## [Unreleased]

### Added
- Classes de transferência de dados `UserResponseDTO` e `UserReferenceDTO` para abstrair, proteger a entidade User e enxugar o tráfego de dados.
- Arquivo `schema.sql` para atuar como documentação estática e versionamento da estrutura do banco de dados.
- Consulta customizada e performática usando Constructor Expression (JPQL) no UserRepository para instanciar DTOs diretamente do banco de dados.

### Changed
- Refatoração dos métodos na classe `UserController` (endpoints GET, POST e PUT) para retornarem UserResponseDTO, impedindo o vazamento da entidade principal.
- Atualização da classe `AccountRequest` para receber um `UserReferenceDTO` em vez da entidade completa.
- Correção de nomenclatura reservada (@Table(name = "users")) e blindagem de CPF na entidade `User`.
- Atualização da entidade User para utilizar `LocalDate`.
- Ajuste no relacionamento *@ManyToOne* da entidade `Account` para utilizar *FetchType.LAZY*.
- Ajuste no `DatabaseSeeder` para utilizar o novo `UserReferenceDTO` durante o povoamento inicial.


### Removed
- Endpoint inseguro e método findAll() de Utilizadores.
- Métodos toString() nativos nas entidades para evitar conflitos de carregamento com o Hibernate (StackOverflowError).

---

## [0.3.0] - 2026-04-04

### Added
- Implementado tratamento de exceção para serviço `update`.
- Implementado tratamento de exceção para serviço `delete`.
- Implementado tratamento de exceção para serviços `findById`, .
- Implementado classe recurso `StandardException` para entrega da exceção em formato JSON.

### Changed
- Organização da classe `DatabaseSeeder`, delegando a criação de `User` para método próprio.
- Domínio da classe `AccountRequest` para DTO.
- Alterado retorno do método `findById` para *.orElseThrow*, evitando o uso de try catch.

### Fixed
- Corrigido camel case em método e atributos.

---

## [0.2.1] - 2026-03-14

### Added
- Adicionado objeto para construção das subclasses de account.
- Implementado endpoint POST, DELETE e PUT para account.
- Implementado endpoint DELETE e PUT para user.
- Implementado endpoint POST para inserção de user com status 201 Created e URI.
- Implementado classe configuração `DatabaseSeeder`, instanciado ao iniciar o sistema.
- Adicionado `controller`, `repository` e `service` inicial para entidade Account.
- Adicionado `controller`, `repository` e `service` inicial para entidade User.

---

## [0.2.0] - 2026-03-12

### Changed
- Configuração do Spring Boot via `application.properties`
- Template `application.example.properties` adicionado para a gestão de credenciais.
- Alterado anotações nas entidades `User` e `Account`;

### Removed
- Exclusão dos arquivos `persistence.xml` e `persistence.example.xml`.

---

## [0.1.1] - 2026-03-12

### Changed
- Configuração do Hibernate via `persistence.xml`, substituindo o arquivo `db.properties`.
- Mapeamento utilizando anotações JPA (`@Entity`, `@Table`, `@Column`, etc.) nas entidades.
- Implementação de herança `SINGLE_TABLE` para a tipos de contas (`Account`, `AccountChecking`, `AccountSaving`).
- Template `persistence.example.xml` adicionado para a gestão de credenciais.

## Removed
- Exclusão dos arquivos `db.properties` e `db.example.properties`
- Remoção de todo o código **JDBC puro** (incluindo `Connection`, `PreparedStatement` e `ResultSet`).
- Remoção das implementações dos DAOs (`UserDaoImplementation` e `AccountDaoImplementation`).
- Remoção das classes utilitárias (`DatabaseUtil`, `UserUtil`, `AccountUtil`), pois o Hibernate passa a fazer as conexões de forma nativa.

# Fixed
- Corrigido versionamento errado em `README` e `CHANGELOG`

---

## [0.1.0] - 2026-03-11

### Added
- Implementação completa dos métodos CRUD utilizando **JDBC puro**.
- Estruturação arquitetural com base no **Padrão DAO (Data Access Object)**, criando interfaces isoladas para `User` e `Account`.
- Criação da classe `DaoFactory` para isolar a instanciação das implementações de banco de dados.
- Implementação de um cache (HashMap) no método `findAll` de Contas para garantir a identidade única do objeto `User` ao montar relacionamentos de Banco de Dados.
- Criação do `DatabaseUtil` para gerenciar de forma segura a abertura e fechamento das conexões e statements do MySQL.
- Script de Testes na classe `Main` para validar as operações de inserção, busca, atualização e exclusão.

### Changed
- Adaptação das classes de Entidade (`User`, `Account`) para conversão de tipos seguros na comunicação com o banco.
- Sobrecarga de métodos no `UserUtil` para permitir buscas com *Aliases* de colunas dinâmicos (evitando colisão de IDs no SQL).