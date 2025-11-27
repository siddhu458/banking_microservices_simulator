package com.example.demo.repo;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.model.Account;

public interface AccountRepository extends MongoRepository<Account, String> {
	  Optional<Account> findByAccountNumber(String accountNumber);
	}

