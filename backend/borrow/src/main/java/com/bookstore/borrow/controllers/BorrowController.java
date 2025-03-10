package com.bookstore.borrow.controllers;

import com.bookstore.borrow.models.http.request.BorrowRequestDto;
import com.bookstore.borrow.services.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/borrow")
public class BorrowController {

    @Autowired
    private BorrowService borrowService;

    // Implement the endpoints here
    @GetMapping("/all")
    public ResponseEntity<?> getAllBorrows() {
        return ResponseEntity.ok(borrowService.getAllBorrows());
    }

    @GetMapping("/borrow/{id}")
    public ResponseEntity<?> getBorrowById(Long id) {
        return ResponseEntity.ok(borrowService.getBorrowById(id));
    }

    @PostMapping("/new")
    public ResponseEntity<?> newBorrow(@RequestBody BorrowRequestDto request) {
        return ResponseEntity.ok(borrowService.newBorrow(request));
    }

    @PutMapping("/return/{id}")
    public ResponseEntity<?> returnBorrow(@PathVariable Long id) {
        return ResponseEntity.ok(borrowService.returnBorrow(id));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getAllBorrowByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(borrowService.getAllBorrowByUserId(userId));
    }

}
