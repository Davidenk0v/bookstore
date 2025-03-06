package com.bookstore.borrow.utils;

import com.bookstore.borrow.models.dtos.BookDto;
import com.bookstore.borrow.models.http.request.BookRequestDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookMapper implements IDtoMapper<Object, BookDto, BookRequestDto> {


    @Override
    public Object dtoToEntity(BookDto dto) {
        return null;
    }

    @Override
    public BookDto entityToDto(Object entity) {
        return null;
    }

    @Override
    public BookRequestDto entityToResponseDto(Object entity, String message, HttpStatus status) {
        return null;
    }

    @Override
    public List<BookDto> entityListToResponseDtoList(List<Object> entityList) {
        return List.of();
    }
}
