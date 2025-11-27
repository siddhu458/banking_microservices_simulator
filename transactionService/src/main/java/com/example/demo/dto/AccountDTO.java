package com.example.demo.dto;

import lombok.Data;

@Data
public class AccountDTO {
    private String accountNumber;
    private Double balance;
    private String accountHolder;

    // getters and setters
}

