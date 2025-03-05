package com.bookstore.books.services.impl;

import com.bookstore.books.models.dtos.BookDto;
import com.bookstore.books.models.http.response.BookResponseDto;
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
            BookDto bookDto = dtoMapper.entityToDto(optionalBook.get());
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
            BookDto bookDto = dtoMapper.entityToDto(optionalBook.get());
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

}
