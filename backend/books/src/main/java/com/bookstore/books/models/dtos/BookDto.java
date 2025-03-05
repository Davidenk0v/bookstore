package com.bookstore.books.models.dtos;

import com.bookstore.books.models.enums.EStatus;
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
