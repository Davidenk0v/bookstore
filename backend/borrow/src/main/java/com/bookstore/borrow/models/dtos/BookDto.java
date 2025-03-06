package com.bookstore.borrow.models.dtos;


import com.bookstore.borrow.models.enums.EStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookDto {


    private String isbn;

    private String title;

    private String author;

    private String category;

    private EStatus status;

    private Integer available_quantity;

    private Integer total_quantity;
    }
