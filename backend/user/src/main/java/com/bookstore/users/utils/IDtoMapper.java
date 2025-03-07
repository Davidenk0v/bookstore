package com.bookstore.users.utils;

import com.bookstore.users.models.entities.User;
import com.bookstore.users.models.http.response.UserDtoResponse;
import org.springframework.http.HttpStatus;

import java.util.List;

public interface IDtoMapper<E, D, R> {
    //Aqui estoy usando el patrón Mapper, que es un patrón de diseño que se utiliza para convertir un objeto en otro.
    E dtoToEntity(D dto);
    D entityToDto(E entity);

    UserDtoResponse entityToResponseDto(User entity);

    List<D> entityListToResponseDtoList(List<E> entityList);
}
