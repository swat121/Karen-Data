package com.karen.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

@Service
@EnableAsync
@EnableScheduling
@RequiredArgsConstructor
public class DynamicSchedulerService {

    private final ThreadPoolTaskScheduler threadPoolTaskScheduler;
    private final TemperatureService temperatureService;

    //TODO: Create bean for this
    private ScheduledFuture<?> scheduledTask;

    @Async
    public void startTask(long updateTime) {
        if (scheduledTask != null) {
            scheduledTask.cancel(true);
        }
        scheduledTask = threadPoolTaskScheduler.scheduleWithFixedDelay(temperatureService::setDegrees, updateTime);
    }

    public void stopTask() {
        if (scheduledTask != null) {
            scheduledTask.cancel(true);
        }
    }
}
