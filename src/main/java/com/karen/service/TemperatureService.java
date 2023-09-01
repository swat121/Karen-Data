package com.karen.service;

import com.karen.dto.TemperatureDto;
import com.karen.model.postgres.Temperature;
import com.karen.repository.TemperatureRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
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
@RequiredArgsConstructor
public class TemperatureService {
    private final ConnectionService connectionService;
    private final TemperatureRepository temperatureRepository;

    private final ModelMapper modelMapper;

    private final Type listType = new TypeToken<List<TemperatureDto>>() {
    }.getType();

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

    public Map<String, Boolean> deleteTemperatures() {
        temperatureRepository.deleteAll();
        Map<String, Boolean> response = new HashMap<>();
        response.put("Delete", Boolean.TRUE);
        return response;
    }
}
