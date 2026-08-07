package com.kharthik.bankingsystem.service;
import com.kharthik.bankingsystem.dto.TransactionDTO;
import com.kharthik.bankingsystem.entity.Transaction;
import com.kharthik.bankingsystem.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;
@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;
    public List<TransactionDTO> getTransactions(String accountNumber) {
        List<Transaction> transactions =
                transactionRepository.findByAccountNumber(accountNumber);

        List<TransactionDTO> transactionDTOList = new ArrayList<>();
        for (Transaction transaction : transactions) {
            TransactionDTO dto = new TransactionDTO();
            dto.setTransactionType(transaction.getTransactionType());
            dto.setAmount(transaction.getAmount());
            dto.setBalanceAfterTransaction(
                    transaction.getBalanceAfterTransaction());
            dto.setTransactionTime(transaction.getTransactionTime());

            transactionDTOList.add(dto);
        }
        return transactionDTOList;



        }
    }
