DROP DATABASE IF EXISTS anotacao;
CREATE DATABASE anotacao;
\C anotacao;



CREATE TABLE anotacoes(
    id        SERIAL PRIMARY KEY,
    titulo    VARCHAR(255) NOT NULL,
    data_hora TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    descricao TEXT NOT NULL,
    cor       VARCHAR(20) NOT NULL,
    foto      BYTEA
);