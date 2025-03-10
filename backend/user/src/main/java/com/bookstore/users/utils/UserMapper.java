package com.bookstore.users.utils;

import com.bookstore.users.models.dtos.UserDto;
import com.bookstore.users.models.entities.User;
import com.bookstore.users.models.http.response.UserDtoResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserMapper implements IDtoMapper<User, UserDto, UserDtoResponse> {


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
                .username(entity.getUsername())
                .password(entity.getPassword())
                .name(entity.getName())
                .email(entity.getEmail())
                .role(entity.getRole())
                .build();
    }

    @Override
    public UserDtoResponse entityToResponseDto(User entity) {
        return UserDtoResponse.builder()
                .username(entity.getUsername())
                .name(entity.getName())
                .email(entity.getEmail())
                .role(entity.getRole())
                .id(entity.getId())
                .build();
    }

    @Override
    public List<UserDtoResponse> entityListToResponseDtoList(List<User> entityList) {
        return entityList.stream()
                .map(this::entityToResponseDto)
                .toList();
    }
}
