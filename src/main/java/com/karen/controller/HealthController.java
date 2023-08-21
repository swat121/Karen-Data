package com.karen.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    @GetMapping("/api/v1/ping")
    public ResponseEntity<String> ping() {
        return ResponseEntity.ok("pong");
    }
}
