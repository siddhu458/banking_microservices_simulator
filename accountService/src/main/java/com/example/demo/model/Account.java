package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "accounts")
public class Account {
  @Id private String id;
  private String accountNumber;
  private String holderName;
  private Double balance;
  // getters/setters or Lombok @Data
}

