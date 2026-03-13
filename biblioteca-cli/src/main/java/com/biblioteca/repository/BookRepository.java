package com.biblioteca.repository;

import com.biblioteca.domain.Book;
import java.util.List;
import java.util.Optional;

public interface BookRepository {
    void save(Book book);

    Optional<Book> findById(int id);

    List<Book> findAll();

    void update(Book book);

    void deleteById(int id);

    void updateAvailableQty(int id, int availableQty);
}
