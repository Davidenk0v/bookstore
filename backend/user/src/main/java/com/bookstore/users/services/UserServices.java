package com.bookstore.users.services;

import com.bookstore.users.models.entities.User;
import com.bookstore.users.models.http.response.UserReponseDto;
import org.springframework.http.ResponseEntity;


public interface UserServices {
    User saveUser(User user);

    ResponseEntity<UserReponseDto> getUser(Long id);

    User updateUser(User user);

    ResponseEntity<?> deleteUser(Long id);

    ResponseEntity<UserReponseDto> getUserByEmail(String email);

    ResponseEntity<UserReponseDto> getAllUsers();
}
