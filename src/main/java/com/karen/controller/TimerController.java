package com.karen.controller;

import com.karen.model.Timer;
import com.karen.service.TimerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class TimerController {

    private final TimerService timerService;

    @GetMapping("/api/v1/timers")
    public List<Timer> getTimers() {
        return timerService.findAllTimers();
    }

    @GetMapping("/api/v1/timers/{micro}")
    public List<Timer> getTimersByMicro(@PathVariable(value = "micro") String micro) {
        return timerService.findTimersByMicro(micro);
    }

    @GetMapping("/api/v1/timers/{micro}/{switcher}")
    public Timer getTimer(@PathVariable(value = "micro") String micro, @PathVariable(value = "switcher") String switcher) {
        return timerService.findTimer(micro, switcher);
    }

    @PostMapping("/api/v1/timer")
    public Timer setTimer(@RequestBody Timer timer) {
        return timerService.saveTimer(timer);
    }
}
