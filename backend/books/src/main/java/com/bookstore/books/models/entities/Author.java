package com.bookstore.books.models.entities;

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

@Document(collection = "authors")
public class Author {

    @Id
    private String id;
    private String name;
    private String bio;
    private String country;
}
