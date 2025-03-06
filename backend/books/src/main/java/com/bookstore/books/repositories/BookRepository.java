package com.bookstore.books.repositories;

import com.bookstore.books.models.entities.Author;
import com.bookstore.books.models.entities.Book;
import com.bookstore.books.models.entities.Category;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@EnableMongoRepositories
public interface BookRepository extends MongoRepository<Book, String> {
    Optional<Book> findByIsbn(String isbn);
    Optional<Book> findByTitle(String title);
    List<Book> findAllByAuthor(Author author);
    List<Book> findAllByCategory(Category category);

    @Query("{ $or: [ {'author.name': { $regex: ?0, $options: 'i' }} ] }")
    List<Book> findByAuthorNameContaining(String authorNameKeyword);

    @Query("{ $or: [ {'category.name': { $regex: ?0, $options: 'i' }} ] }")
    List<Book> findByCategoryNameContaining(String categoryNameKeyword);
}
