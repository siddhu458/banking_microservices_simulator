package com.example.demo.service;


import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.model.Account;
import com.example.demo.repo.AccountRepository;

@Service
public class AccountService {
  private final AccountRepository repo;

  public AccountService(AccountRepository repo) { this.repo = repo; }

  public Account createAccount(Account account) {
    account.setBalance(account.getBalance() == null ? 0.0 : account.getBalance());
    // you may want to ensure unique accountNumber
    return repo.save(account);
  }

  public Account getByAccountNumber(String accNo) {
    return repo.findByAccountNumber(accNo)
               .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found"));
  }

  public Account updateBalance(String accNo, double newBalance) {
    Account a = getByAccountNumber(accNo);
    a.setBalance(newBalance);
    return repo.save(a);
  }

  public void updateBalanceDelta(String accNo, double delta) {
    Account a = getByAccountNumber(accNo);
    a.setBalance(a.getBalance() + delta);
    repo.save(a);
  }
}
