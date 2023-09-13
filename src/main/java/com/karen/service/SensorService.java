package com.karen.service;

import com.karen.dto.sensor.SensorRequestDto;
import com.karen.dto.sensor.SensorResponseDto;
import com.karen.model.postgres.Sensor;
import com.karen.repository.SensorRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SensorService {
    private final SensorRepository sensorRepository;

    private final ModelMapper modelMapper;

    private final Type listType = new TypeToken<List<SensorResponseDto>>() {
    }.getType();

    public SensorResponseDto saveSensorData(SensorRequestDto sensorRequestDto) {
        LocalDate myDate = LocalDate.now(ZoneId.of("Europe/Kiev"));
        LocalTime myTime = LocalTime.now(ZoneId.of("Europe/Kiev"));
        Sensor sensor = sensorRepository.save(Sensor.builder()
                .date(myDate)
                .time(myTime)
                .name(sensorRequestDto.getName())
                .data(sensorRequestDto.getData())
                .sensorId(sensorRequestDto.getSensorId())
                .build());
        return modelMapper.map(sensor, SensorResponseDto.class);
    }

    public List<SensorResponseDto> getSensorData() {
        return modelMapper.map(sensorRepository.findAll(), listType);
    }

    public Map<String, Boolean> deleteSensorsData() {
        sensorRepository.deleteAll();
        Map<String, Boolean> response = new HashMap<>();
        response.put("Delete", Boolean.TRUE);
        return response;
    }
}
