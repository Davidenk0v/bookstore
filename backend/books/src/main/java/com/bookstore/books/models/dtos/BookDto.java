package com.bookstore.books.models.dtos;

import com.bookstore.users.models.enums.ERole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookDto {

        private Long id;

        private String name;

        private String email;

        private ERole role;
    }
