package com.kharthik.bankingsystem.dto;
import lombok.Data;
import jakarta.validation.constraints.NotBlank;

@Data
public class CustomerDTO {
    @NotBlank(message = "Customer Name is required")
    private String customerName;
    private String email;
    private String phoneNumber;
    private String address;

}
