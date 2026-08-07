package com.kharthik.bankingsystem.controller;

import com.kharthik.bankingsystem.dto.AccountDTO;
import com.kharthik.bankingsystem.dto.AmountDTO;
import com.kharthik.bankingsystem.dto.TransactionResponseDTO;
import com.kharthik.bankingsystem.dto.TransferDTO;
import com.kharthik.bankingsystem.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @PostMapping
    public String createAccount(@RequestBody AccountDTO accountDTO) {
        return accountService.createAccount(accountDTO);

    }
    @PostMapping("/{id}/deposit")
    public ResponseEntity<TransactionResponseDTO> deposit(@PathVariable Long id, @RequestBody AmountDTO amountDTO) {
        TransactionResponseDTO response= accountService.deposit(id, amountDTO);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/{id}/balance")
    public Double checkBalance(@PathVariable Long id) {
        return accountService.checkBalance(id);
    }
    @PostMapping("/{id}/withdraw")
    public  TransactionResponseDTO withdraw(@PathVariable Long id,
                           @RequestBody AmountDTO amountDTO) {
        return accountService.withdraw(id, amountDTO);
    }
    @PostMapping("/transfer")
    public ResponseEntity<TransactionResponseDTO> transfer(
            @RequestBody TransferDTO transferDTO) {

        TransactionResponseDTO response =
                accountService.transfer(transferDTO);

        return ResponseEntity.ok(response);
    }


    }
