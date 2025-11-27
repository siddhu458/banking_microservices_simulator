package com.example.demo.feignClient;

import com.example.demo.dto.AccountDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@FeignClient(name = "account-service")
public interface AccountClient {

    @PutMapping("/api/accounts/{accountNumber}/balance")
    AccountDTO updateBalance(@PathVariable("accountNumber") String accountNumber,
                             @RequestBody Map<String, Double> body);

    @GetMapping("/api/accounts/{accountNumber}")
    AccountDTO getAccount(@PathVariable("accountNumber") String accountNumber);
}
