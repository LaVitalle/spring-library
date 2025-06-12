INSERT INTO usuarios (id, nome, senha, email, endereco, telefone) VALUES
  (1, 'João da Silva', '123456', 'joao@email.com', 'Rua das Flores, 123', '11999999999'),
  (2, 'Maria Oliveira', 'abcdef', 'maria@email.com', 'Av. Brasil, 456', '11988888888');

INSERT INTO livros (id, titulo, autor, ano_publicacao, isbn, editora) VALUES
  (1, 'O Senhor dos Anéis', 'J.R.R. Tolkien', 1954, '978-8578270698', 'Martins Fontes'),
  (2, 'Dom Casmurro', 'Machado de Assis', 1899, '978-8535910665', 'Globo'),
  (3, '1984', 'George Orwell', 1949, '978-0451524935', 'Companhia das Letras');

INSERT INTO emprestimos (id, data_emprestimo, data_devolucao, usuario_id) VALUES
  (1, '2025-06-10', '2025-06-20', 1),
  (2, '2025-06-11', '2025-06-21', 2);

INSERT INTO emprestimo_itens (id, livro_id, emprestimo_id) VALUES
  (1, 1, 1),
  (2, 2, 1),
  (3, 3, 2);
