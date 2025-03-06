package com.bookstore.users.utils;

import com.bookstore.users.models.dtos.UserDto;
import com.bookstore.users.models.entities.User;
import com.bookstore.users.models.http.response.UserReponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserMapper implements IDtoMapper<User, UserDto, UserReponseDto> {


    @Override
    public User dtoToEntity(UserDto dto) {
        return User.builder()
                .id(dto.getId())
                .name(dto.getName())
                .email(dto.getEmail())
                .role(dto.getRole())
                .build();
    }

    @Override
    public UserDto entityToDto(User entity) {
        return UserDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .email(entity.getEmail())
                .role(entity.getRole())
                .build();
    }

    @Override
    public UserReponseDto entityToResponseDto(User entity, String message, HttpStatus status) {
        return null;
    }

    @Override
    public List<UserDto> entityListToResponseDtoList(List<User> entityList) {
        return entityList.stream()
                .map(this::entityToDto)
                .toList();
    }
}
