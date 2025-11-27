package com.example.demo.model;

import java.sql.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "transactions")
public class Transaction {
  @Id private String id;
  private String transactionId;
  private String type; // DEPOSIT, WITHDRAW, TRANSFER
  private Double amount;
  private Date timestamp;
  private String status; // SUCCESS, FAILED
  private String sourceAccount;
  private String destinationAccount;
}

