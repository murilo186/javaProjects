package com.biblioteca.repository;

import com.biblioteca.config.ConnectionFactory;
import com.biblioteca.domain.Book;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SQLiteBookRepository implements BookRepository {

    @Override
    public void save(Book book) {
        String sql = """
            INSERT INTO livros (id, titulo, edicao, descricao, qtd_total, qtd_disp)
            VALUES (?, ?, ?, ?, ?, ?)
            """;

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, book.getId());
            statement.setString(2, book.getTitle());
            statement.setString(3, book.getEdition());
            statement.setString(4, book.getDescription());
            statement.setInt(5, book.getTotalQty());
            statement.setInt(6, book.getAvailableQty());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException("Failed to save book with id " + book.getId(), e);
        }
    }

    @Override
    public Optional<Book> findById(int id) {
        String sql = """
            SELECT id, titulo, edicao, descricao, qtd_total, qtd_disp
            FROM livros
            WHERE id = ?
            """;

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);

            try (ResultSet rs = statement.executeQuery()) {
                if (!rs.next()) {
                    return Optional.empty();
                }
                return Optional.of(mapRowToBook(rs));
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Failed to find book by id " + id, e);
        }
    }

    @Override
    public List<Book> findAll() {
        String sql = """
            SELECT id, titulo, edicao, descricao, qtd_total, qtd_disp
            FROM livros
            ORDER BY id
            """;

        List<Book> books = new ArrayList<>();
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                books.add(mapRowToBook(rs));
            }
            return books;
        } catch (SQLException e) {
            throw new IllegalStateException("Failed to list books", e);
        }
    }

    @Override
    public void update(Book book) {
        String sql = """
            UPDATE livros
            SET titulo = ?, edicao = ?, descricao = ?, qtd_total = ?, qtd_disp = ?
            WHERE id = ?
            """;

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, book.getTitle());
            statement.setString(2, book.getEdition());
            statement.setString(3, book.getDescription());
            statement.setInt(4, book.getTotalQty());
            statement.setInt(5, book.getAvailableQty());
            statement.setInt(6, book.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException("Failed to update book with id " + book.getId(), e);
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE FROM livros WHERE id = ?";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException("Failed to delete book with id " + id, e);
        }
    }

    @Override
    public void updateAvailableQty(int id, int availableQty) {
        String sql = "UPDATE livros SET qtd_disp = ? WHERE id = ?";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, availableQty);
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException(
                "Failed to update available quantity for book id " + id,
                e
            );
        }
    }

    private Book mapRowToBook(ResultSet rs) throws SQLException {
        return new Book(
            rs.getInt("id"),
            rs.getString("titulo"),
            rs.getString("edicao"),
            rs.getString("descricao"),
            rs.getInt("qtd_total"),
            rs.getInt("qtd_disp")
        );
    }
}
