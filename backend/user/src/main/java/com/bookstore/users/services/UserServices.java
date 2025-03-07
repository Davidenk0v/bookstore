package com.bookstore.users.services;

import com.bookstore.users.models.entities.User;
import com.bookstore.users.models.http.response.UserReponse;
import org.springframework.http.ResponseEntity;


public interface UserServices {
    UserReponse saveUser(User user);

    ResponseEntity<UserReponse> getUser(Long id);

    User updateUser(User user);

    ResponseEntity<?> deleteUser(Long id);

    ResponseEntity<UserReponse> getUserByEmail(String email);

    ResponseEntity<UserReponse> getAllUsers();

    //METODOS PARA COMUNICACIÓN CON EL SERVICIO DE BORROW
    UserReponse findUserById(Long id);

    UserReponse findUserByEmail(String email);

    UserReponse findUserByUsername(String username);

    UserReponse findAllUsers();
}
