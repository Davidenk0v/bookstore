package com.bookstore.users.utils;

import org.springframework.http.HttpStatus;

import java.util.List;

public interface IDtoMapper<E, D, R> {
    //Aqui estoy usando el patrón Mapper, que es un patrón de diseño que se utiliza para convertir un objeto en otro.
    E dtoToEntity(D dto);
    D entityToDto(E entity);

    R entityToResponseDto(E entity, String message, HttpStatus status);

    List<D> entityListToResponseDtoList(List<E> entityList);
}
