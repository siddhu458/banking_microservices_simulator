package com.example.demo;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/notifications")
@Slf4j
public class NotificationController {

  @PostMapping("/send")
  public ResponseEntity<Void> send(@RequestBody Map<String,String> payload) {
    String acc = payload.get("accountNumber");
    String type = payload.get("type");
    String amount = payload.get("amount");
    String status = payload.get("status");

    log.info("Notification => account={} type={} amount={} status={}", acc, type, amount, status);
    return ResponseEntity.ok().build();
  }
}
