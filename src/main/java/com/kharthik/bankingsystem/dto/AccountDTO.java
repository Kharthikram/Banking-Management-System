package com.kharthik.bankingsystem.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountDTO {

    @NotBlank(message = "Account Number is required")
    private String accountNumber;

    @NotNull(message = "Balance is required")
    private Double balance;
    @NotNull(message = "Customer Id is required")
    private Long customerId;






}
