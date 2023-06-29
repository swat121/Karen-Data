package com.karen.controller;

import com.karen.dto.TemperatureDto;
import com.karen.service.TemperatureService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
public class TemperatureAPIController {

    private final TemperatureService temperatureService;

    @PostMapping("/api/v1/temperature")
    public TemperatureDto addTemp(@RequestParam(value = "degrees", required = false) Double degrees) {
        return temperatureService.saveTemperature(degrees);
    }

    @GetMapping("/api/v1/temperatures")
    public List<TemperatureDto> getTemps() {
        return temperatureService.getTemperature();
    }

    @DeleteMapping("/api/v1/temperatures")
    public Map<String, Boolean> deleteTemps() {
        return temperatureService.deleteTemps();
    }
}
