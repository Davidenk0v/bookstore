package com.bookstore.borrow.repositories;

import com.bookstore.borrow.models.entities.Borrow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BorrowRepository extends JpaRepository<Borrow, Long> {
    Optional<Borrow> findByUserId(Long userId);
    Optional<Borrow> findByBookId(String bookId);

}
