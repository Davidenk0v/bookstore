package com.bookstore.books.models.entities;

import com.bookstore.books.models.enums.EStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Document(collection = "books")
public class Book {

    @Id
    private String isbn;

    private String title;

    private Author author;

    private Category category;

    private EStatus status;

    private Integer available_quantity;

    private Integer total_quantity;
}
