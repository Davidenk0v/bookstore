package com.bookstore.borrow.services.impl;

import com.bookstore.borrow.clients.BookClient;
import com.bookstore.borrow.clients.UserClient;
import com.bookstore.borrow.models.dtos.BorrowDto;
import com.bookstore.borrow.models.entities.Borrow;
import com.bookstore.borrow.models.enums.EStatusBorrow;
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

import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BorrowServiceImpl implements BorrowService {
    
    @Autowired
    private KafkaService kafkaService;

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
        UserRequestDto userRequest = userClient.getUserById(request.getUserId());
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
                kafkaService.sendMessage("Borrow created with id: " + response.getData().get(0).getId() + " by user: " + response.getData().get(0).getUserId());
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
        Date now = new Date();
        int days = request.getDays();
        Date returnPredictionDate = Date.from(now.toInstant().plus(days, ChronoUnit.DAYS));
        Borrow borrow = Borrow.builder()
                .userId(request.getUserId())
                .bookId(request.getBookId())
                .borrowDate(now)
                .returnPredictionDate(returnPredictionDate)
                .status(EStatusBorrow.BORROWED)
                .penalty(0)
                .build();
        borrowRepository.save(borrow);
        BorrowDto borrowDto = borrowMapper.entityToDto(borrow);
        return borrowDto;
    }

    @Override
    public ResponseEntity<?> returnBorrow(Long id) {
        BorrowResponseDto response = new BorrowResponseDto();
        Optional<Borrow> optional = borrowRepository.findById(id);
        if(optional.isPresent()){
            Borrow borrow = optional.get();
            borrow.setStatus(EStatusBorrow.RETURNED);
            borrowRepository.save(borrow);
            response.setMessage("Borrow returned");
            response.setStatus(HttpStatus.OK);
            kafkaService.sendMessage("Borrow returned with id: " + borrow.getId() + " by user: " + borrow.getUserId());
        }else{
            response.setMessage("Borrow not found");
            response.setStatus(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @Override
    public ResponseEntity<?> getAllBorrowByUserId(Long userId) {
        BorrowResponseDto response = new BorrowResponseDto();
        List<Borrow> borrows = borrowRepository.findAllByUserId(userId);
        if(borrows.isEmpty()){
            response.setMessage("No borrows found");
            response.setStatus(HttpStatus.NOT_FOUND);
        }else{
            List<BorrowDto> dtos = borrowMapper.entityListToResponseDtoList(borrows);
            response.setMessage("Borrows found");
            response.setStatus(HttpStatus.OK);
            response.setData(dtos);
        }
        return ResponseEntity.status(response.getStatus()).body(response);
    }

}
