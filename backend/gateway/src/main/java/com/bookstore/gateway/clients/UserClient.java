package com.bookstore.gateway.clients;

import com.bookstore.gateway.dtos.UserDto;
import com.bookstore.gateway.dtos.http.UserRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "user-service" , url = "http://localhost:8082/api/v1/user")
public interface UserClient {

    @GetMapping("/all-users")
    public UserRequest getAllUsers();

    @GetMapping("/find-by-id/{id}")
    public UserRequest getUserById(@PathVariable Long id);

    @GetMapping("/find-by-email/{email}")
    public UserRequest getUserByEmail(@PathVariable String email);

    @PostMapping("/add")
    public UserRequest saveUser(@RequestBody UserDto user);
}
