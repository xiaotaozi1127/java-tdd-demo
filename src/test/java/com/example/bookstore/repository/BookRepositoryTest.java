package com.example.bookstore.repository;

import com.example.bookstore.model.Book;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.stream.StreamSupport;

@ExtendWith(SpringExtension.class)
@DataJpaTest
//@DataJpaTest provides some standard setup needed for testing the persistence layer:
//
//configuring H2, an in-memory database
//setting Hibernate, Spring Data, and the DataSource
//performing an @EntityScan
//turning on SQL logging
public class BookRepositoryTest {

    @Autowired
    BookRepository bookRepository;

    @Test
    @Sql(scripts = {"classpath:initBookTable.sql"})
    public void shouldFetchAllTheBooks() {
        Iterable<Book> all = bookRepository.findAll();
        long count = StreamSupport.stream(all.spliterator(), false).count();
        Assertions.assertEquals(2, count);
    }

    @Test
    @Sql(scripts = {"classpath:initBookTable.sql"})
    public void shouldFetchBooksByTitle() {
        List<Book> allByTitle = bookRepository.findAllByTitle("test");
        Assertions.assertEquals(1, allByTitle.size());
    }
}
