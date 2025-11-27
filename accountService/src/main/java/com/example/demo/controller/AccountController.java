package com.example.demo.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Account;
import com.example.demo.service.AccountService;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
  private final AccountService service;
  public AccountController(AccountService service) { this.service = service; }

  @PostMapping
  public ResponseEntity<Account> create(@RequestBody Account account) {
    return ResponseEntity.status(HttpStatus.CREATED).body(service.createAccount(account));
  }

  @GetMapping("/{accountNumber}")
  public Account get(@PathVariable String accountNumber) {
    return service.getByAccountNumber(accountNumber);
  }

  @PutMapping("/{accountNumber}/balance")
  public Account updateBalance(@PathVariable String accountNumber, @RequestBody Map<String, Double> body) {
    Double balance = body.get("balance");
    return service.updateBalance(accountNumber, balance);
  }

  @PutMapping("/{accountNumber}/status")
  public ResponseEntity<Void> updateStatus(@PathVariable String accountNumber, @RequestBody Map<String, String> body) {
    // implement activate/deactivate
    return ResponseEntity.ok().build();
  }
}

