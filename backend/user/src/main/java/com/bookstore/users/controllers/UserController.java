package com.bookstore.users.controllers;

import com.bookstore.users.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserServices userServices;

    @GetMapping("/all")
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok(userServices.getAllUsers());
    }

    @GetMapping("/userId/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id) {
        return userServices.getUser(id);
    }

    @GetMapping("/delete")
    public ResponseEntity<?> deleteUser(Long id) {
        return userServices.deleteUser(id);
    }


}
