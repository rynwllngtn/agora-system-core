CREATE TABLE users (
    id BINARY(16) PRIMARY KEY,
    cpf CHAR(11) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    user_name VARCHAR(255),
    birth_date DATE NOT NULL,
    active BOOLEAN NOT NULL
);

CREATE TABLE accounts (
    id BINARY(16) PRIMARY KEY,
    holder_id BINARY(16) NOT NULL,
    balance DECIMAL(9,2) NOT NULL,
    transfer_limit DECIMAL(9,2) NOT NULL,
    transfer_limit_cap DECIMAL(9,2) NOT NULL,
    account_type VARCHAR(16) NOT NULL,
    FOREIGN KEY (holder_id) REFERENCES users(id)
);