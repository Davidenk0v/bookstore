package com.bookstore.users.controllers;

import com.bookstore.users.models.dtos.UserDto;
import com.bookstore.users.models.entities.User;
import com.bookstore.users.models.http.response.UserReponseDto;
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

    //ENDPOINT PARA COMUNICAR CON EL SERVICIO DE BORROW
    @GetMapping("/find-by-id/{id}")
    public UserReponseDto findUserById(@PathVariable Long id) {
        return userServices.findUserById(id);
    }

    @PostMapping("/add")
    public UserReponseDto saveUser(@RequestBody User user) {
        return userServices.saveUser(user);
    }

    @GetMapping("/find-by-email/{email}")
    public UserReponseDto findUserByEmail(@PathVariable String email) {
        return userServices.findUserByEmail(email);
    }

    @GetMapping("/all-users")
    public UserReponseDto findAllUsers() {
        return userServices.findAllUsers();
    }


}
