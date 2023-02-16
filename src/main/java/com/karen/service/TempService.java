package com.karen.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

@Service
@EnableAsync
@EnableScheduling
@RequiredArgsConstructor
public class TempService {
    private final ConnectionService connectionService;
    private final DataService dataService;
    private final long updateTime = 3600000;
    private final long initTime = 120000;

    private double getDegrees() {
        try {
            return Double.parseDouble(connectionService.getResponseFromService("karen", "/patric/sensor/temperature", String.class));
        } catch (ResourceAccessException | IllegalStateException e) {
            return -1000;
        }
    }

    @Async
    @Scheduled(fixedRate = updateTime, initialDelay = initTime)
    public void setDegrees() {
        dataService.saveTemperature(getDegrees());
    }
}
