package com.bookstore.borrow.clients;

import com.bookstore.borrow.models.http.request.UserRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service", url = "http://localhost:8082/api/v1/user")
public interface UserClient {

    @GetMapping("/userId/{id}")
    public ResponseEntity<UserRequestDto> getUserById(@PathVariable Long id);

}
