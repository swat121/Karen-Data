package com.karen.controller;

import com.karen.dto.sensor.SensorRequestDto;
import com.karen.dto.sensor.SensorResponseDto;
import com.karen.service.SensorService;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
public class SensorController {

    private final SensorService sensorService;
    private static final Logger LOG = LogManager.getRootLogger();

    @PostMapping("/api/v1/sensors")
    public SensorResponseDto setSensorData(@RequestBody SensorRequestDto request) {
        return sensorService.saveSensorData(request);
    }

    @GetMapping("/api/v1/sensors")
    public List<SensorResponseDto> getSensorsData() {
        return sensorService.getSensorData();
    }

    @DeleteMapping("/api/v1/sensors")
    public Map<String, Boolean> deleteSensorsData() {
        return sensorService.deleteSensorsData();
    }
}
