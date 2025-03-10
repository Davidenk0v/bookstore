package com.bookstore.books.models.http.response;

import com.bookstore.books.models.dtos.BookDto;
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
public class BookResponseDto {


    private String message;

    private List<BookDto> data;

    private HttpStatus status;
}
