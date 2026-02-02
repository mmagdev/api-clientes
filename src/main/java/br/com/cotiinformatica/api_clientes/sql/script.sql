-- SCRIPT PARA CRIAÇÃO DA TABELA DE CLIENTES DO BANCO
--Para alterar entradas da tabela: ALTER TABLE add CONSTRAINT (pesquisar melhor como usar)

CREATE TABLE clientes(
id              SERIAL          PRIMARY KEY,
nome            VARCHAR(150)    NOT NULL,
email           VARCHAR(50)     NOT NULL UNIQUE,
telefone        VARCHAR(20)     NOT NULL,
datacadastro    TIMESTAMP       DEFAULT CURRENT_TIMESTAMP,
ativo           INT             DEFAULT 1



)