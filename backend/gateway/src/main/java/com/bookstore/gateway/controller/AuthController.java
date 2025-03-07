package com.bookstore.gateway.controller;

import com.bookstore.gateway.dtos.UserDto;
import com.bookstore.gateway.dtos.http.AuthRequest;
import com.bookstore.gateway.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/register")
    private ResponseEntity<?> register(@RequestBody UserDto user) throws Exception{
        return authService.register(user);
    }


    @PostMapping("/login")
    private ResponseEntity<?> login (@RequestBody AuthRequest request) throws Exception{
        return authService.login(request);
    }

//    @PostMapping("/refresh")
//    private ResponseEntity<?> refresh (@RequestBody RefreshRequest request) throws Exception{
//        return authService.refresh(request);
//    }
}
