package com.bookstore.gateway.dtos.http;

import com.bookstore.gateway.dtos.UserDto;
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
public class UserRequest {

    private String message;

    private List<UserDto> data;

    private HttpStatus status;
}
