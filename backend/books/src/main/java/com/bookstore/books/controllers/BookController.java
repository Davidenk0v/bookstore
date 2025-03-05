package com.bookstore.books.controllers;

import com.bookstore.books.models.entities.Book;
import com.bookstore.books.services.BookServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
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
        return ResponseEntity.ok(bookServices.getBookByIbsn(isbn));
    }

    @GetMapping("/delete")
    public ResponseEntity<?> deleteBook(String id) {
        return bookServices.deleteBook(id);
    }


}
