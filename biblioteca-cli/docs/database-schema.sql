PRAGMA foreign_keys = ON;

CREATE TABLE IF NOT EXISTS livros (
    id INTEGER PRIMARY KEY,
    titulo TEXT NOT NULL,
    edicao TEXT,
    descricao TEXT,
    qtd_total INTEGER NOT NULL CHECK (qtd_total >= 0),
    qtd_disp INTEGER NOT NULL CHECK (qtd_disp >= 0 AND qtd_disp <= qtd_total),
    created_at TEXT NOT NULL DEFAULT (datetime('now')),
    updated_at TEXT NOT NULL DEFAULT (datetime('now'))
);

CREATE TRIGGER IF NOT EXISTS trg_livros_updated_at
AFTER UPDATE ON livros
FOR EACH ROW
BEGIN
    UPDATE livros
    SET updated_at = datetime('now')
    WHERE id = NEW.id;
END;

CREATE TABLE IF NOT EXISTS emprestimos (
    codigo INTEGER PRIMARY KEY AUTOINCREMENT,
    livro_id INTEGER NOT NULL,
    nome_pessoa TEXT NOT NULL,
    data_emprestimo TEXT NOT NULL,
    data_prevista TEXT NOT NULL,
    data_devolucao TEXT,
    status TEXT NOT NULL CHECK (status IN ('ABERTO', 'DEVOLVIDO')),
    CONSTRAINT fk_emprestimos_livro
        FOREIGN KEY (livro_id) REFERENCES livros(id) ON DELETE RESTRICT,
    CONSTRAINT ck_datas_validas
        CHECK (date(data_prevista) >= date(data_emprestimo))
);

CREATE INDEX IF NOT EXISTS idx_emprestimos_livro_id ON emprestimos (livro_id);
CREATE INDEX IF NOT EXISTS idx_emprestimos_status ON emprestimos (status);
CREATE INDEX IF NOT EXISTS idx_emprestimos_nome_pessoa ON emprestimos (nome_pessoa);
