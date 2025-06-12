-- Criação da tabela de empréstimos
CREATE TABLE IF NOT EXISTS emprestimos (
    id BIGSERIAL PRIMARY KEY,
    data_emprestimo DATE NOT NULL,
    data_devolucao DATE,
    usuario_id BIGINT NOT NULL,
    CONSTRAINT fk_emprestimo_usuario FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
);

-- Criação da tabela de itens de empréstimo
CREATE TABLE IF NOT EXISTS emprestimo_itens (
    id BIGSERIAL PRIMARY KEY,
    livro_id BIGINT NOT NULL,
    emprestimo_id BIGINT NOT NULL,
    CONSTRAINT fk_item_livro FOREIGN KEY (livro_id) REFERENCES livros(id),
    CONSTRAINT fk_item_emprestimo FOREIGN KEY (emprestimo_id) REFERENCES emprestimos(id)
);
