package com.bookstore.users.models.http.response;

import com.bookstore.users.models.enums.ERole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDtoResponse {


        private Long id;

        private String name;

        private String username;

        private String email;

        private ERole role;
}
