package com.karen.service;

import com.karen.model.Timer;
import com.karen.repository.TimerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.ZoneId;
import java.util.List;

@Service
@AllArgsConstructor
public class TimerService {

    private final TimerRepository timerRepository;

    public List<Timer> findAllTimers() {
        return timerRepository.findAll();
    }

    public List<Timer> findTimersByMicro(String micro) {
        return timerRepository.findAllByMicroName(micro);
    }

    public Timer findTimer(String micro, String switcher) {
        return timerRepository.findTimerByMicroNameAndSwitcherName(micro, switcher);
    }

    public Timer saveTimer(Timer timer) {
        LocalTime myTime = LocalTime.now(ZoneId.of("Europe/Kiev"));
        timer.setTimeOff(myTime.plusHours(timer.getTimerTime()));
        return timerRepository.save(timer);
    }
}
