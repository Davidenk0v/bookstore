package com.bookstore.borrow.models.http.response;

import com.bookstore.borrow.models.dtos.BorrowDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BorrowResponseDto {

    private String message;

    private List<BorrowDto> data;

    private HttpStatus status;
}
