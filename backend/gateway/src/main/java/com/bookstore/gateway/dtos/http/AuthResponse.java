package com.bookstore.gateway.dtos.http;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {

    private String message;
    private String token;
    private String refreshToken;
    private String status;
    private String error;
}
