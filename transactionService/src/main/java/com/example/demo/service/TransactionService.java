package com.example.demo.service;

import java.sql.Date;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.AccountDTO;
import com.example.demo.feignClient.AccountClient;
import com.example.demo.feignClient.NotificationClient;
import com.example.demo.model.Transaction;
import com.example.demo.repo.TransactionRepository;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.validation.constraints.NotBlank;

@Service
public class TransactionService {

    private final TransactionRepository txRepo;
    private final AccountClient accountClient;
    private final NotificationClient notificationClient;

    public TransactionService(TransactionRepository txRepo,
                              AccountClient accountClient,
                              NotificationClient notificationClient) {
        this.txRepo = txRepo;
        this.accountClient = accountClient;
        this.notificationClient = notificationClient;
    }

    @Transactional
    public Transaction deposit(String accountNumber, double amount) {
        // Get account via Feign
        AccountDTO account = accountClient.getAccount(accountNumber);
        double newBalance = account.getBalance() + amount;

        // Update account balance
        accountClient.updateBalance(
                accountNumber,
                Collections.singletonMap("balance", newBalance)
        );

        // Save transaction
        Transaction tx = new Transaction();
        tx.setTransactionId("TXN-" + System.currentTimeMillis());
        tx.setType("DEPOSIT");
        tx.setAmount(amount);
        tx.setTimestamp(new Date(System.currentTimeMillis()));
        tx.setStatus("SUCCESS");
        tx.setSourceAccount(accountNumber);
        tx.setDestinationAccount(null);
        txRepo.save(tx);

        // Notify
        sendNotificationSafe(accountNumber, "DEPOSIT", amount, "SUCCESS");

        return tx;
    }

    @CircuitBreaker(name = "notifyCB", fallbackMethod = "notifyFallback")
    public void sendNotificationSafe(String accountNumber, String type, double amount, String status) {
        notificationClient.sendNotification(Map.of(
                "accountNumber", accountNumber,
                "type", type,
                "amount", String.valueOf(amount),
                "status", status
        ));
    }

    public void notifyFallback(String accountNumber, String type, double amount, String status, Throwable t) {
        // Logging or fallback logic
    }

    public Transaction withdraw(String accountNumber, Double amount) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public List<Transaction> getTransactionsForAccount(@NotBlank String accountNumber) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public Transaction transfer(String sourceAccount, String destinationAccount, Double amount) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public Object getByTransactionId(@NotBlank String transactionId) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public Object getTransactionsByAccount(String accountNumber) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
