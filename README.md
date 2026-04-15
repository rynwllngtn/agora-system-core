# Agora System Core (API)

![Java](https://img.shields.io/badge/Java-25-orange?style=for-the-badge&logo=java)
![Status](https://img.shields.io/badge/Status-EM%20EVOLUÇÂO-success?style=for-the-badge)
![Version](https://img.shields.io/badge/version-v0.3.2-blue?style=for-the-badge)

O **Agora System Core** é o microsserviço principal do ecossistema Agora System, responsável por gerir as regras de negócio centrais, operações financeiras e o ciclo de vida dos usuários.  
O objetivo deste módulo é construir um domínio robusto, aplicando fortemente os conceitos de **Domain-Driven Design (DDD)** e **Clean Code**.

---

![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white)

---

## Aprendizado e Arquitetura

O desenvolvimento desta versão marcou uma transição profunda na arquitetura do sistema, movendo a lógica de negócio dos *Services* para onde ela realmente pertence.

- **Domain-Driven Design (DDD) & Domínio Rico:** As entidades (`User`, `Account`) deixaram de ser meras caixas de dados (modelo anêmico) e passaram a encapsular os seus próprios comportamentos e validações internas (ex: `deposit()`, `withdraw()`, `deactivate()`).
- **Gerenciamento de Estado (Soft Delete):** Eliminação de exclusões físicas (`DELETE`) na base de dados. Implementação de exclusão lógica baseada em máquina de estados (`ACTIVE`, `SUSPENDED`, `CLOSED`), garantindo integridade do histórico e auditoria.
- **Modernização com Records:** Substituição massiva de classes tradicionais na camada de transferência de dados por `records` nativos do Java, garantindo DTOs imutáveis, seguros e de sintaxe limpa.
- **Tratamento Global de Exceções:** Uso de `@ControllerAdvice` para capturar exceções específicas de negócio (como `InsufficientFundsException` e `InvalidAmountException`), blindando o domínio e padronizando as respostas de erro para o Front-end.

---

## 📡 Endpoints da API

A API foi refatorada para utilizar operações de domínio específicas em vez de atualizações genéricas, refletindo intenções de negócio claras.

### Usuários (`/users`)
| Método | Rota | Descrição |
|---|---|---|
| `POST` | `/users` | Cria um novo usuário no sistema. |
| `GET` | `/users/{id}` | Retorna os dados cadastrais e o status atual do usuário. |
| `PATCH`| `/users/{id}/deactivate` | Executa a exclusão lógica (*Soft Delete*), alterando o status para `CLOSED`. |
| `PATCH`| `/users/{id}/reactivate` | Restaura o acesso do usuário, alterando o status para `ACTIVE`. |

### Contas (`/accounts`)
| Método | Rota | Descrição |
|---|---|---|
| `POST` | `/accounts` | Abre uma nova conta vinculada a um usuário. |
| `GET` | `/accounts/{id}` | Consulta o saldo e os dados da conta. |
| `POST` | `/accounts/{id}/deposit` | Realiza um aporte financeiro. Exige regras de negócio (valores > 0). |
| `POST` | `/accounts/{id}/withdraw` | Realiza um saque. Protegido contra saldo negativo (`InsufficientFundsException`). |

---

## Exemplos de Cargas

Com a adoção dos `records`, os payloads de entrada e saída são enxutos e estritamente validados. Operações de alteração de estado não exigem envio do objeto completo.

**Criação de Usuário (`POST /users`)**
```json
{
  "cpd": "11122233344", 
  "password": "12345678",
  "fullName": "Ryan Wellington",
  "birthDate": "07-01-2002"
}
```

*(Nota: As rotas de `PATCH` em `/users` não requerem corpo na requisição, pois a intenção de negócio já está clara na própria URL).*

**Criação de Conta (`POST /users`)**
```json
{
  "holder": "2c8069aa-feb3-4e85-80b4-718459218c99", 
  "balance": 0,
  "transferLimit": 10000,
  "transferLimitCap": 10000,
  "accountType": "CHECKING"
}
```

**Depósito em Conta (`POST /accounts/{id}/deposit`)**
```json
{
  "amount": 250.00
}
```

**Saque em Conta (`POST /accounts/{id}/withdraw`)**
```json
{
  "amount": 50.00
}
```

---

## Como testar a aplicação localmente?

Para rodar a API na sua máquina, você precisará do **Java 25** e de uma instância do **MySQL** (ou Docker) rodando localmente.

1. Clone o repositório:
   ```bash
   git clone [https://github.com/rynwllngtn/agora-system-core.git](https://github.com/rynwllngtn/agora-system-core.git)
   ```
2. Renomeie o arquivo `application.example.properties` (ou `.yaml`) para `application.properties`.
3. Insira as suas credenciais do MySQL local e o nome da base de dados no novo arquivo.
4. Rode a aplicação usando o Maven Wrapper na raiz do projeto:
   ```bash
   ./mvnw spring-boot:run
   ```
   ou inicie a classe Main diretamente pela sua IDE (como no IntelliJ).
5. Acesse `http://localhost:8080/users` no seu Postman ou navegador para interagir com a API.