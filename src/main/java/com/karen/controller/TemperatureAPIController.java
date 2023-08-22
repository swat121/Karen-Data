package com.karen.controller;

import com.karen.dto.TemperatureDto;
import com.karen.service.TemperatureService;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
public class TemperatureAPIController {

    private final TemperatureService temperatureService;
    private static final Logger LOG = LogManager.getRootLogger();

    @PostMapping("/api/v1/temperature")
    public TemperatureDto setTemp(@RequestParam(value = "degrees", required = false) Double degrees) {
        LOG.info("POST: temperature = " + degrees);
        return temperatureService.saveTemperature(degrees);
    }

    @GetMapping("/api/v1/temperatures")
    public List<TemperatureDto> getTemps() {
        LOG.info("GET: temperatures");
        return temperatureService.getTemperature();
    }

    @DeleteMapping("/api/v1/temperatures")
    public Map<String, Boolean> deleteTemps() {
        LOG.info("DELETE: temperatures");
        return temperatureService.deleteTemperatures();
    }
}
