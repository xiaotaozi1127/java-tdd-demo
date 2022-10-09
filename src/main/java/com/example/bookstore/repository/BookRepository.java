package com.example.bookstore.repository;

import com.example.bookstore.model.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface BookRepository extends CrudRepository<Book, UUID> {
    List<Book> findAllByTitle(String title);
}
