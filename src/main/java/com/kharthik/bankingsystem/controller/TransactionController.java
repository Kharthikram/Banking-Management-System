package com.kharthik.bankingsystem.controller;
import com.kharthik.bankingsystem.dto.TransactionDTO;
import com.kharthik.bankingsystem.entity.Transaction;
import com.kharthik.bankingsystem.service.TransactionService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/transactions")
@SecurityRequirement(name = "bearerAuth")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;
    @GetMapping("/{accountNumber}")

    public List<TransactionDTO> getTransactions(
            @PathVariable String accountNumber) {
        return transactionService.getTransactions(accountNumber);
    }
}
