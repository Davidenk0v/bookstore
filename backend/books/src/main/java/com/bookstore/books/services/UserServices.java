package com.bookstore.books.services;

import com.bookstore.users.models.entities.User;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface UserServices {
    User saveUser(User user);

    User getUser(Long id);

    User updateUser(User user);

    ResponseEntity<?> deleteUser(Long id);

    User getUserByEmail(String email);

    List<User> getAllUsers();
}
