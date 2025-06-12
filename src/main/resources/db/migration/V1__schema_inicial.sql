CREATE TABLE IF NOT EXISTS livros (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    ano_publicacao INTEGER,
    autor VARCHAR(80),
    editora VARCHAR(80),
    isbn VARCHAR(50),
    titulo VARCHAR(80)
);

CREATE TABLE IF NOT EXISTS usuarios (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(80),
    endereco VARCHAR(255),
    nome VARCHAR(80),
    senha VARCHAR(80),
    telefone VARCHAR(20)
);
