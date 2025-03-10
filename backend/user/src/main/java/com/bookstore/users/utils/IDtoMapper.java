package com.bookstore.users.utils;

import com.bookstore.users.models.entities.User;
import com.bookstore.users.models.http.response.UserDtoResponse;

import java.util.List;

public interface IDtoMapper<E, D, R> {
    //Aqui estoy usando el patrón Mapper, que es un patrón de diseño que se utiliza para convertir un objeto en otro.
    E dtoToEntity(D dto);
    D entityToDto(E entity);

    UserDtoResponse entityToResponseDto(User entity);

    List<UserDtoResponse> entityListToResponseDtoList(List<E> entityList);
}
