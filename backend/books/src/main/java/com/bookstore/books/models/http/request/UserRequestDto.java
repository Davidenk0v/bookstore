package com.bookstore.books.models.http.request;

import com.bookstore.users.models.dtos.UserDto;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequestDto {

    @NotNull
    private String message;

    private UserDto user;

    @NotNull
    private HttpStatus status;
}
