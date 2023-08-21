package com.karen.service;

import com.karen.dto.TimerDto;
import com.karen.model.postgres.Timer;
import com.karen.repository.TimerRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.lang.reflect.Type;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.List;

@Service
@AllArgsConstructor
public class TimerService {

    private final ModelMapper modelMapper;

    private final TimerRepository timerRepository;

    private final Type listType = new TypeToken<List<TimerDto>>() {}.getType();

    public List<TimerDto> findAllTimers() {
        return modelMapper.map(timerRepository.findAll(), listType);
    }

    public List<TimerDto> findTimersByMicro(String micro) {
        return modelMapper.map(timerRepository.findAllByMicroName(micro), listType);
    }

    public TimerDto findTimer(String micro, String switcher) {
        return modelMapper.map(timerRepository.findTimerByMicroNameAndSwitcherName(micro, switcher)
                .orElseThrow(() -> new EntityNotFoundException("Timer with board: " + micro + " and switcher: " + switcher + " - Not found")), TimerDto.class);
    }

    public TimerDto saveTimer(Timer timer) {
        LocalTime myTime = LocalTime.now(ZoneId.of("Europe/Kiev"));
        timer.setTimeOff(myTime.plusHours(timer.getTimerTime()));
        return modelMapper.map(timerRepository.save(timer), TimerDto.class);
    }

    public int updateTimer(TimerDto timer) {
        return timerRepository.updateTimerInfo(timer.getStatus(), timer.getMicroName(), timer.getSwitcherName());
    }

    public void deleteTimerByMicroName(String name) {
        timerRepository.deleteByMicroName(name);
    }
}
