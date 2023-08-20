package com.karen.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ScheduledFuture;

@Service
@EnableAsync
@EnableScheduling
@RequiredArgsConstructor
public class DynamicSchedulerService {

    private final ThreadPoolTaskScheduler threadPoolTaskScheduler;
    private final TemperatureService temperatureService;
    private Map<String, ScheduledFuture<?>> scheduledTasks = new HashMap<>();

    @Async
    public synchronized CompletableFuture<Void> startTask(String taskName, long updateTime) {
        return CompletableFuture.runAsync(() -> {
            if(scheduledTasks.get(taskName) != null) {
                stopTask(taskName);
            }
            ScheduledFuture<?> futureTask = null;

            switch (taskName) {
                case "temperatureTask":
                    futureTask = threadPoolTaskScheduler.scheduleWithFixedDelay(temperatureService::setDegrees, updateTime);
                    break;
                default:
                    throw new IllegalArgumentException("Unknown task name: " + taskName);
            }

            scheduledTasks.put(taskName, futureTask);
        });
    }

    public synchronized void stopTask(String taskName) {
        ScheduledFuture<?> existingTask = scheduledTasks.get(taskName);
        if (existingTask != null) {
            existingTask.cancel(true);
            scheduledTasks.remove(taskName);
        } else {
            throw new IllegalArgumentException("Unknown task name: " + taskName);
        }
    }
}
