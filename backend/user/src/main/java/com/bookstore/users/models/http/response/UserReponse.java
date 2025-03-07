package com.bookstore.users.models.http.response;

import com.bookstore.users.models.dtos.UserDto;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserReponse {

    @NotNull
    private String message;

    private List<?> data;

    @NotNull
    private HttpStatus status;
}
