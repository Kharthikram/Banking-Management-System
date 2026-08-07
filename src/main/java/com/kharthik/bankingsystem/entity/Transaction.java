package com.kharthik.bankingsystem.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;
    private String accountNumber;
    private String transactionType;
    private Double amount;
    private Double balanceAfterTransaction;
    private LocalDateTime transactionTime;

}