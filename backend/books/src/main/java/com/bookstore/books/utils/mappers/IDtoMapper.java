package com.bookstore.books.utils.mappers;

import com.bookstore.books.models.dtos.BookDto;
import com.bookstore.books.models.entities.Book;
import com.bookstore.books.models.http.response.BookResponseDto;
import org.springframework.http.HttpStatus;

import java.util.List;

public interface IDtoMapper<E, D, R> {
    //Aqui estoy usando el patrón Mapper, que es un patrón de diseño que se utiliza para convertir un objeto en otro.
    E dtoToEntity(D dto);
    BookDto entityToDto(E entity);

    BookResponseDto entityToResponseDto(E entity, String message, HttpStatus status);

    List<BookDto> entityListToResponseDtoList(List<E> entityList);
}
