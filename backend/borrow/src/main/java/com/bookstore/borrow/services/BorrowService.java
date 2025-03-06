package com.bookstore.borrow.services;

import com.bookstore.borrow.models.entities.Borrow;
import com.bookstore.borrow.models.http.request.BorrowRequestDto;
import com.bookstore.borrow.models.http.response.BorrowResponseDto;
import org.springframework.http.ResponseEntity;

public interface BorrowService {


    ResponseEntity<?> newBorrow(BorrowRequestDto request);

    ResponseEntity<BorrowResponseDto> getBorrowById(Long id);

    ResponseEntity<BorrowResponseDto> getAllBorrows();
}
