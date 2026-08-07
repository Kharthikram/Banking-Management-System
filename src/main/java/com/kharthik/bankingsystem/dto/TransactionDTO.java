package com.kharthik.bankingsystem.dto;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
public class TransactionDTO {

    private String transactionType;
    private Double amount;
    private Double balanceAfterTransaction;
    private LocalDateTime transactionTime;

}
