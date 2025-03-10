package com.bookstore.borrow.models.dtos;

import com.bookstore.borrow.models.enums.EStatus;
import com.bookstore.borrow.models.enums.EStatusBorrow;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BorrowDto {

    private Long id;

    private String bookId;

    private Long userId;

    private Date borrowDate;

    private Date returnPredictionDate;

    private Date returnDate;

    private EStatusBorrow status;

    private Integer penalty;
}
