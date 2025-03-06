package com.bookstore.books.repositories;

import com.bookstore.books.models.entities.Author;
import com.bookstore.books.models.entities.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.List;
import java.util.Optional;

@EnableMongoRepositories
public interface AuthorRepository extends MongoRepository<Author, String> {
    Optional<Author> findByName(String name);

    List<Author> findAllByCountry(String author);
}