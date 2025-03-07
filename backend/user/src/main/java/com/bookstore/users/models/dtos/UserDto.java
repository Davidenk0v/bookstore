package com.bookstore.users.models.dtos;

import com.bookstore.users.models.enums.ERole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {

        private Long id;

        private String name;

        private String username;

        private String email;

        private ERole role;
    }
