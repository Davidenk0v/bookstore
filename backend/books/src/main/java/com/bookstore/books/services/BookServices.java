package com.bookstore.books.services;

import com.bookstore.books.models.entities.Book;
import com.bookstore.books.models.http.response.BookResponseDto;
import org.springframework.http.ResponseEntity;


public interface BookServices {
    Book saveBook(Book book);

    ResponseEntity<BookResponseDto> getBookByIbsn(String id);

    Book updateBook(Book book);

    ResponseEntity<?> deleteBook(String id);

    ResponseEntity<BookResponseDto> getBookByTitle(String title);

    ResponseEntity<BookResponseDto> getAllBooks();

    ResponseEntity<BookResponseDto> getBooksByAuthor(String author);

    ResponseEntity<BookResponseDto> getBooksByCategory(String categoryName);

    BookResponseDto findBookByTitle(String title);

    BookResponseDto findBookByIbsn(String ibsn);
}
