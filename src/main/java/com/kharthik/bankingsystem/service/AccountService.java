package com.kharthik.bankingsystem.service;
import com.kharthik.bankingsystem.dto.AmountDTO;
import com.kharthik.bankingsystem.dto.TransactionResponseDTO;
import com.kharthik.bankingsystem.dto.TransferDTO;
import com.kharthik.bankingsystem.entity.Customer;
import com.kharthik.bankingsystem.repository.CustomerRepository;
import com.kharthik.bankingsystem.repository.TransactionRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.kharthik.bankingsystem.repository.AccountRepository;
import com.kharthik.bankingsystem.dto.AccountDTO;
import com.kharthik.bankingsystem.entity.Account;
import java.time.LocalDateTime;
import org.springframework.transaction.annotation.Transactional;
import com.kharthik.bankingsystem.entity.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service
public class AccountService {

    private static final Logger logger =
            LoggerFactory.getLogger(AccountService.class);

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private TransactionRepository transactionRepository;

    public String createAccount(AccountDTO accountDTO) {
        Account account = new Account();
        account.setAccountNumber(accountDTO.getAccountNumber());
        account.setBalance(accountDTO.getBalance());
        Customer customer = customerRepository.findById(accountDTO.getCustomerId())
                .orElse(null);
        if (customer == null) {
            return "Customer Not Found";
        }

        account.setCustomer(customer);
        accountRepository.save(account);

        return "Account Created Successfully";
    }

    public TransactionResponseDTO   deposit(Long id, AmountDTO amountDTO) {
        Account account = accountRepository.findById(id).orElse(null);
        if (account == null) {
            TransactionResponseDTO response = new TransactionResponseDTO();
            response.setStatus("FAILED");
            response.setMessage("Account Not Found");
            response.setCurrentBalance(null);
            response.setTimestamp(LocalDateTime.now());
            return response;
        }
        account.setBalance(account.getBalance() + amountDTO.getAmount());
        accountRepository.save(account);
        saveTransaction(
                account.getAccountNumber(),
                "DEPOSIT",
                amountDTO.getAmount(),
                account.getBalance()
        );
        TransactionResponseDTO response = new TransactionResponseDTO();
        response.setStatus("SUCCESS");
        response.setMessage("Amount Deposited Successfully");
        response.setCurrentBalance(account.getBalance());

        response.setTimestamp(LocalDateTime.now());
           return response;
    }

    public Double checkBalance(Long id) {
        Account account = accountRepository.findById(id).orElse(null);
        if (account == null) {
            return null;
        }
        return account.getBalance();
    }

    public TransactionResponseDTO withdraw(Long id, AmountDTO amountDTO) {
        Account account = accountRepository.findById(id).orElse(null);
        TransactionResponseDTO response = new TransactionResponseDTO();
        if (account == null) {
            response.setMessage("Account Not Found");
            response.setCurrentBalance(null);
            return response;
        }
        if (account.getBalance() < amountDTO.getAmount()) {
            response.setMessage("Insufficient Balance");
            response.setCurrentBalance(account.getBalance());
            return response;
        }
        account.setBalance(account.getBalance() - amountDTO.getAmount());
        accountRepository.save(account);
        saveTransaction(
                account.getAccountNumber(),
                "WITHDRAW",
                amountDTO.getAmount(),
                account.getBalance()
        );
        response.setMessage("Amount Withdrawn Successfully");
        response.setCurrentBalance(account.getBalance());
           return response;

    }
    @Transactional
    public TransactionResponseDTO transfer(TransferDTO transferDTO) {

        Account fromAccount = accountRepository
                .findByAccountNumber(transferDTO.getFromAccountNumber())
                .orElse(null);

        Account toAccount = accountRepository
                .findByAccountNumber(transferDTO.getToAccountNumber())
                .orElse(null);

        TransactionResponseDTO response = new TransactionResponseDTO();

        if (fromAccount == null || toAccount == null) {
            response.setStatus("FAILED");
            response.setMessage("Account Not Found");
            response.setCurrentBalance(null);
            response.setTimestamp(LocalDateTime.now());
            return response;
        }

        if (fromAccount.getBalance() < transferDTO.getAmount()) {

            response.setStatus("FAILED");
            response.setMessage("Insufficient Balance");
            response.setCurrentBalance(fromAccount.getBalance());
            response.setTimestamp(LocalDateTime.now());

            return response;
        }

        fromAccount.setBalance(
                fromAccount.getBalance() - transferDTO.getAmount());

        toAccount.setBalance(
                toAccount.getBalance() + transferDTO.getAmount());
        accountRepository.save(fromAccount);
        saveTransaction(
                fromAccount.getAccountNumber(),
                "TRANSFER_OUT",
                transferDTO.getAmount(),
                fromAccount.getBalance()
        );
        accountRepository.save(toAccount);
        saveTransaction(
                toAccount.getAccountNumber(),
                "TRANSFER_IN",
                transferDTO.getAmount(),
                toAccount.getBalance()
        );

        response.setStatus("SUCCESS");
        response.setMessage("Amount Transferred Successfully");
        response.setCurrentBalance(fromAccount.getBalance());
        response.setTimestamp(LocalDateTime.now());

        return response;
    }
        private void saveTransaction(String accountNumber,
                String transactionType,
                Double amount,
                Double balanceAfterTransaction) {

            Transaction transaction = new Transaction();

            transaction.setAccountNumber(accountNumber);
            transaction.setTransactionType(transactionType);
            transaction.setAmount(amount);
            transaction.setBalanceAfterTransaction(balanceAfterTransaction);
            transaction.setTransactionTime(LocalDateTime.now());

            transactionRepository.save(transaction);
            logger.info("Transaction saved successfully for account {}", accountNumber);
        }













}
