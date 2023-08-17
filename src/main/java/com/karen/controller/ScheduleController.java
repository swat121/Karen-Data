package com.karen.controller;

import com.karen.service.DynamicSchedulerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ScheduleController {
    private final DynamicSchedulerService dynamicSchedulerService;

    @PostMapping("/api/v1/schedule/start")
    public ResponseEntity<String> startTask(@RequestParam long updateTime) {
        dynamicSchedulerService.startTask(updateTime);
        return ResponseEntity.ok("Task started");
    }

    @PostMapping("/api/v1/schedule/stop")
    public ResponseEntity<String> stopTask() {
        dynamicSchedulerService.stopTask();
        return ResponseEntity.ok("Task stopped");
    }
}
