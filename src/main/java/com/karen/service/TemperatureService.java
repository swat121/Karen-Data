package com.karen.service;

import com.karen.dto.TemperatureDto;
import com.karen.model.Temperature;
import com.karen.repository.TemperatureRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@EnableAsync
@EnableScheduling
@RequiredArgsConstructor
public class TemperatureService {
    private final ConnectionService connectionService;
    private final TemperatureRepository temperatureRepository;

    private final ModelMapper modelMapper;

    private final Type listType = new TypeToken<List<TemperatureDto>>() {}.getType();

    private final long updateTime = 3600000;
    private final long initTime = 120000;

    private double getDegrees() {
        try {
            return Double.parseDouble(connectionService.getResponseFromService("karen", "/patric/sensor/temperature", String.class));
        } catch (ResourceAccessException | IllegalStateException e) {
            return -1000;
        }
    }

    public TemperatureDto saveTemperature(double degrees) {
        LocalDate myDate = LocalDate.now(ZoneId.of("Europe/Kiev"));
        LocalTime myTime = LocalTime.now(ZoneId.of("Europe/Kiev"));
        Temperature temperature = temperatureRepository.save(Temperature.builder()
                .date(myDate)
                .time(myTime)
                .degreesCelsius(degrees)
                .build());
        return modelMapper.map(temperature, TemperatureDto.class);
    }

    public List<TemperatureDto> getTemperature() {
        return modelMapper.map(temperatureRepository.findAll(), listType);
    }

    public Map<String, Boolean> deleteTemps() {
        temperatureRepository.deleteAll();
        Map<String, Boolean> response = new HashMap<>();
        response.put("Delete", Boolean.TRUE);
        return response;
    }

    @Async
    @Scheduled(fixedRate = updateTime, initialDelay = initTime)
    public void setDegrees() {
        saveTemperature(getDegrees());
    }
}
