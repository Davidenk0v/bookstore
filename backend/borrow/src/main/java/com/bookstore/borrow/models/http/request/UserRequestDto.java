package com.bookstore.borrow.models.http.request;


import com.bookstore.borrow.models.dtos.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequestDto {


    private String message;

    private List<UserDto> data;


    private HttpStatus status;
}
