package com.bookstore.books.repositories;

import com.bookstore.books.models.entities.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@EnableMongoRepositories
public interface BookRepository extends MongoRepository<Book, String> {
    Optional<Book> findByIsbn(String isbn);
    Optional<Book> findByTitle(String title);
    List<Book> findAllByAuthor(String author);
}
