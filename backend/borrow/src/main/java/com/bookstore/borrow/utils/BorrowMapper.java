package com.bookstore.borrow.utils;

import com.bookstore.borrow.models.dtos.BorrowDto;
import com.bookstore.borrow.models.entities.Borrow;
import com.bookstore.borrow.models.http.response.BorrowResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BorrowMapper implements IDtoMapper<Borrow, BorrowDto, BorrowResponseDto>{

    @Override
    public Borrow dtoToEntity(BorrowDto dto) {
        return null;
    }

    @Override
    public BorrowDto entityToDto(Borrow entity) {
        return BorrowDto.builder()
                .id(entity.getId())
                .userId(entity.getUserId())
                .bookId(entity.getBookId())
                .borrowDate(entity.getBorrowDate())
                .returnDate(entity.getReturnDate())
                .status(entity.getStatus())
                .returnPredictionDate(entity.getReturnPredictionDate())
                .returnDate(entity.getReturnDate())
                .penalty(entity.getPenalty())
                .build();
    }

    @Override
    public BorrowResponseDto entityToResponseDto(Borrow entity, String message, HttpStatus status) {
        return null;
    }

    @Override
    public List<BorrowDto> entityListToResponseDtoList(List<Borrow> entityList) {
        return entityList.stream()
                .map(this::entityToDto)
                .collect(Collectors
                        .toList());
    }
}
