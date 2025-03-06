package com.bookstore.books.repositories;

import com.bookstore.books.models.entities.Book;
import com.bookstore.books.models.entities.Category;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.List;
import java.util.Optional;

@EnableMongoRepositories
public interface CategoryRepository extends MongoRepository<Category, String> {
    Optional<Category> findByName(String name);
}
