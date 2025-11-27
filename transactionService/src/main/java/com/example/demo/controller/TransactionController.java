package com.example.demo.controller;

import com.example.demo.dto.DepositRequest;
import com.example.demo.dto.TransferRequest;
import com.example.demo.dto.WithdrawRequest;
import com.example.demo.model.Transaction;
import com.example.demo.service.TransactionService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService txService;

    public TransactionController(TransactionService txService) {
        this.txService = txService;
    }

    // ----------------------- DEPOSIT -----------------------
    @PostMapping("/deposit")
    public ResponseEntity<Transaction> deposit(@RequestBody DepositRequest request) {
        Transaction tx = txService.deposit(
                request.getAccountNumber(),
                request.getAmount()
        );
        return ResponseEntity.ok(tx);
    }

    // ----------------------- WITHDRAW -----------------------
    @PostMapping("/withdraw")
    public ResponseEntity<Transaction> withdraw(@RequestBody WithdrawRequest request) {
        Transaction tx = txService.withdraw(
                request.getAccountNumber(),
                request.getAmount()
        );
        return ResponseEntity.ok(tx);
    }

    // ----------------------- TRANSFER -----------------------
    @PostMapping("/transfer")
    public ResponseEntity<Transaction> transfer(@RequestBody TransferRequest request) {
        Transaction tx = txService.transfer(
                request.getSourceAccount(),
                request.getDestinationAccount(),
                request.getAmount()
        );
        return ResponseEntity.ok(tx);
    }

 
}
