package com.karen.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {
    private static final Logger LOG = LogManager.getRootLogger();

    @GetMapping("/api/v1/ping")
    public ResponseEntity<String> ping() {
        LOG.info("Received GET request on /api/v1/ping. Checking service availability.");
        return ResponseEntity.ok("pong");
    }
}
