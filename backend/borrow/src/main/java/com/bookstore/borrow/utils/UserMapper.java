package com.bookstore.borrow.utils;


import com.bookstore.borrow.models.dtos.UserDto;
import com.bookstore.borrow.models.http.request.UserRequestDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserMapper implements IDtoMapper<Object, UserDto, UserRequestDto> {


    @Override
    public Object dtoToEntity(UserDto dto) {
        return null;
    }

    @Override
    public UserDto entityToDto(Object entity) {
        return null;
    }

    @Override
    public UserRequestDto entityToResponseDto(Object entity, String message, HttpStatus status) {
        return null;
    }

    @Override
    public List<UserDto> entityListToResponseDtoList(List<Object> entityList) {
        return List.of();
    }
}
