package com.bookstore.books.models.http.response;

import com.bookstore.books.models.dtos.BookDto;
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
public class BookResponseDto {

    @NotNull
    private String message;

    private BookDto book;

    @NotNull
    private HttpStatus status;
}
