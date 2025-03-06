package com.bookstore.borrow.models.entities;

import com.bookstore.borrow.models.enums.EStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity
@Table(name = "borrows")
public class Borrow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String bookId;

    @Column
    private Long userId;

    @Column
    private Date borrowDate;

    @Column
    private Date returnPredictionDate;

    @Column
    private Date returnDate;

    @Column
    private EStatus status;

    @Column
    private Integer penalty;
}
