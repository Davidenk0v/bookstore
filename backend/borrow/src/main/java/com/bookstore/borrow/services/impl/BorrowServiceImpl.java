package com.bookstore.borrow.services.impl;

import com.bookstore.borrow.clients.BookClient;
import com.bookstore.borrow.clients.UserClient;
import com.bookstore.borrow.models.dtos.BorrowDto;
import com.bookstore.borrow.models.entities.Borrow;
import com.bookstore.borrow.models.http.request.BookRequestDto;
import com.bookstore.borrow.models.http.request.BorrowRequestDto;
import com.bookstore.borrow.models.http.request.UserRequestDto;
import com.bookstore.borrow.models.http.response.BorrowResponseDto;
import com.bookstore.borrow.repositories.BorrowRepository;
import com.bookstore.borrow.services.BorrowService;
import com.bookstore.borrow.utils.BorrowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BorrowServiceImpl implements BorrowService {

    @Autowired
    private UserClient userClient;

    @Autowired
    private BookClient bookClient;

    @Autowired
    private BorrowRepository borrowRepository;

    @Autowired
    private BorrowMapper borrowMapper;

    @Override
    public ResponseEntity<?> newBorrow(BorrowRequestDto request) {
        BorrowResponseDto response = new BorrowResponseDto();
        Object user = userClient.getUserById(request.getUserId());
        UserRequestDto userRequest = userClient.getUserById(request.getUserId()).getBody();
        BookRequestDto bookRequest = bookClient.getBookByIsbn(request.getBookId());
        if(userRequest.getStatus() == HttpStatus.NOT_FOUND){
            response.setMessage("User not found");
            response.setStatus(HttpStatus.NOT_FOUND);
        }else if(bookRequest.getStatus() == HttpStatus.NOT_FOUND){
            response.setMessage("Book not found");
            response.setStatus(HttpStatus.NOT_FOUND);
        } else {
            try {
                response.setData(List.of(createNewBorrow(request)));
                response.setMessage("Borrow created");
                response.setStatus(HttpStatus.CREATED);
            }catch (Exception e){
                response.setMessage("Error creating borrow");
                response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @Override
    public ResponseEntity<BorrowResponseDto> getBorrowById(Long id) {
        BorrowResponseDto borrowResponseDto = new BorrowResponseDto();
        Optional<Borrow> optional = borrowRepository.findById(id);
        if (optional.isPresent()) {
            BorrowDto dto = borrowMapper.entityToDto(optional.get());
            borrowResponseDto.setMessage("Borrow found");
            borrowResponseDto.setStatus(HttpStatus.OK);
            borrowResponseDto.setData(List.of(dto));
        } else {
            borrowResponseDto.setMessage("Borrow not found");
            borrowResponseDto.setStatus(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.status(borrowResponseDto.getStatus()).body(borrowResponseDto);
    }

    @Override
    public ResponseEntity<BorrowResponseDto> getAllBorrows(){
        BorrowResponseDto borrowResponseDto = new BorrowResponseDto();
        List<Borrow> borrows = borrowRepository.findAll();
        if (borrows.isEmpty()) {
            borrowResponseDto.setMessage("No borrows found");
            borrowResponseDto.setStatus(HttpStatus.NOT_FOUND);
        } else {
            List<BorrowDto> dtos = borrowMapper.entityListToResponseDtoList(borrows);
            borrowResponseDto.setMessage("Borrows found");
            borrowResponseDto.setStatus(HttpStatus.OK);
            borrowResponseDto.setData(dtos);
        }
        return ResponseEntity.status(borrowResponseDto.getStatus()).body(borrowResponseDto);
    }

    private BorrowDto createNewBorrow (BorrowRequestDto request) {
        Borrow borrow = Borrow.builder()
                .userId(request.getUserId())
                .bookId(request.getBookId())
                .borrowDate(new Date())
                .build();
        borrowRepository.save(borrow);
        BorrowDto borrowDto = borrowMapper.entityToDto(borrow);
        return borrowDto;
    }

}
