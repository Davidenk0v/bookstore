package com.bookstore.books.services.impl;

import com.bookstore.books.models.dtos.BookDto;
import com.bookstore.books.models.entities.Author;
import com.bookstore.books.models.entities.Category;
import com.bookstore.books.models.http.response.BookResponseDto;
import com.bookstore.books.repositories.AuthorRepository;
import com.bookstore.books.repositories.CategoryRepository;
import com.bookstore.books.services.BookServices;
import com.bookstore.books.models.entities.Book;
import com.bookstore.books.repositories.BookRepository;
import com.bookstore.books.utils.mappers.IDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServicesImpl implements BookServices {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private IDtoMapper dtoMapper;

    @Override
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public ResponseEntity<BookResponseDto> getBookByIbsn(String ibsn) {
        BookResponseDto bookResponseDto = new BookResponseDto();
        Optional<Book> optionalBook = bookRepository.findByIsbn(ibsn);
        if(optionalBook.isPresent()) {
            bookResponseDto.setMessage("Book found");
            bookResponseDto.setStatus(HttpStatus.OK);
            BookDto bookDto = (BookDto) dtoMapper.entityToDto(optionalBook.get());
            bookResponseDto.setData(List.of(bookDto));
            return ResponseEntity.ok(bookResponseDto);
        }
        bookResponseDto.setMessage("Book not found");
        bookResponseDto.setStatus(HttpStatus.NOT_FOUND);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(bookResponseDto);
    }

    @Override
    public Book updateBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public ResponseEntity<?> deleteBook(String id) {
        bookRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<BookResponseDto> getBookByTitle(String title) {
        BookResponseDto bookResponseDto = new BookResponseDto();
        Optional<Book> optionalBook = bookRepository.findByTitle(title);
        if(optionalBook.isPresent()) {
            bookResponseDto.setMessage("Book found");
            bookResponseDto.setStatus(HttpStatus.OK);
            BookDto bookDto = (BookDto) dtoMapper.entityToDto(optionalBook.get());
            bookResponseDto.setData(List.of(bookDto));
            return ResponseEntity.ok(bookResponseDto);
        }
        bookResponseDto.setMessage("Books not found");
        bookResponseDto.setStatus(HttpStatus.NOT_FOUND);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(bookResponseDto);
    }

    @Override
    public ResponseEntity<BookResponseDto> getAllBooks() {
        BookResponseDto bookResponseDto = new BookResponseDto();
        List<Book> books = bookRepository.findAll();
        if(books.isEmpty()) {
            bookResponseDto.setMessage("No books found");
            bookResponseDto.setStatus(HttpStatus.NOT_FOUND);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(bookResponseDto);
        }
        bookResponseDto.setMessage("Books found");
        bookResponseDto.setStatus(HttpStatus.OK);
        bookResponseDto.setData(dtoMapper.entityListToResponseDtoList(books));
        return ResponseEntity.ok(bookResponseDto);
    }

    @Override
    public ResponseEntity<BookResponseDto> getBooksByAuthor(String authorName) {
        BookResponseDto bookResponseDto = new BookResponseDto();
        List<Book> books = bookRepository.findByAuthorNameContaining(authorName);
        if(books.isEmpty()) {
            bookResponseDto.setMessage("No books found for author: " + authorName);
            bookResponseDto.setStatus(HttpStatus.NOT_FOUND);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(bookResponseDto);
        }
        bookResponseDto.setMessage("Books found for author: " + authorName);
        bookResponseDto.setStatus(HttpStatus.OK);
        bookResponseDto.setData(dtoMapper.entityListToResponseDtoList(books));
        return ResponseEntity.ok(bookResponseDto);
    }

    @Override
    public ResponseEntity<BookResponseDto> getBooksByCategory(String categoryName) {
        BookResponseDto bookResponseDto = new BookResponseDto();
        List<Book> books = bookRepository.findByCategoryNameContaining(categoryName);
        if(books.isEmpty()) {
            bookResponseDto.setMessage("No books found for category: " + categoryName);
            bookResponseDto.setStatus(HttpStatus.NOT_FOUND);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(bookResponseDto);
        }
        bookResponseDto.setMessage("Books found");
        bookResponseDto.setStatus(HttpStatus.OK);
        bookResponseDto.setData(dtoMapper.entityListToResponseDtoList(books));
        return ResponseEntity.ok(bookResponseDto);
    }

}
