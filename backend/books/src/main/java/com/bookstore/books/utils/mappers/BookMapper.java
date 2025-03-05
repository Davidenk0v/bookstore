package com.bookstore.books.utils.mappers;

import com.bookstore.books.models.dtos.BookDto;
import com.bookstore.books.models.entities.Book;
import com.bookstore.books.models.http.response.BookResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookMapper implements IDtoMapper<Book, BookDto, BookResponseDto> {



    @Override
    public Book dtoToEntity(BookDto dto) {
        return Book.builder()
                .isbn(dto.getIsbn())
                .title(dto.getTitle())
                .author(dto.getAuthor())
                .available_quantity(dto.getAvailable_quantity())
                .total_quantity(dto.getTotal_quantity())
                .category(dto.getCategory())
                .status(dto.getStatus())
                .build();
    }

    @Override
    public BookDto entityToDto(Book entity) {
        return BookDto.builder()
                .isbn(entity.getIsbn())
                .title(entity.getTitle())
                .author(entity.getAuthor())
                .available_quantity(entity.getAvailable_quantity())
                .total_quantity(entity.getTotal_quantity())
                .category(entity.getCategory())
                .status(entity.getStatus())
                .build();
    }

    @Override
    public BookResponseDto entityToResponseDto(Book entity, String message, HttpStatus status) {
        return null;
    }

    @Override
    public List<BookDto> entityListToResponseDtoList(List<Book> entityList) {
        return entityList
                .stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }
}
