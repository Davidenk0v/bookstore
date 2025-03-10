package com.bookstore.borrow.models.http.request;

import com.bookstore.borrow.models.enums.EStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BorrowRequestDto {

    private Long id;

    private String bookId;

    private Long userId;

    private Integer days;

}
