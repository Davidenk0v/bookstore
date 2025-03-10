package com.bookstore.books.controllers;

import com.bookstore.books.models.entities.Book;
import com.bookstore.books.models.http.response.BookResponseDto;
import com.bookstore.books.services.BookServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/book")
public class BookController {

    @Autowired
    private BookServices bookServices;

    @GetMapping("/all")
    public ResponseEntity<?> getAllBooks() {
        return ResponseEntity.ok(bookServices.getAllBooks());
    }

    @PostMapping("/add")
    public ResponseEntity<?> saveBook(@RequestBody Book book) {
        return ResponseEntity.ok(bookServices.saveBook(book));
    }

    @GetMapping("/{isbn}")
    public ResponseEntity<?> getBook(@PathVariable String isbn) {
        return bookServices.getBookByIbsn(isbn);
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<?> getBookByTitle(@PathVariable String title) {
        return bookServices.getBookByTitle(title);
    }

    @GetMapping("/delete")
    public ResponseEntity<?> deleteBook(String id) {
        return bookServices.deleteBook(id);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<?> getBooksByCategory(@PathVariable String category) {
        return bookServices.getBooksByCategory(category);
    }

    @GetMapping("/author/{author}")
    public ResponseEntity<?> getBooksByAuthor(@PathVariable String author) {
        return bookServices.getBooksByAuthor(author);
    }

    //ENDPOINT PARA COMUNICAR CON EL SERVICIO DE BORROW
    @GetMapping("/find-by-isbn/{isbn}")
    public BookResponseDto findBookByIsbn(@PathVariable String isbn) {
        return bookServices.findBookByIbsn(isbn);
    }

    @GetMapping("/find-by-title/{title}")
    public BookResponseDto findBookByTitle(@PathVariable String title) {
        return bookServices.findBookByTitle(title);
    }

}
