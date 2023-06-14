package com.karen.controller;

import com.karen.dto.TemperatureDto;
import com.karen.endpoints.TempEndpoints;
import com.karen.model.Temperature;
import com.karen.service.ClientService;
import com.karen.service.TempService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
public class TemperatureAPIController {

    private final TempService tempService;

    @PostMapping(TempEndpoints.API_TEMP)
    public TemperatureDto addTemp(@RequestParam(value = "degrees", required = false) int degrees) {
        return tempService.saveTemperature(degrees);
    }

    @GetMapping(TempEndpoints.API_TEMPS)
    public List<TemperatureDto> getTemps() {
        return tempService.getTemperature();
    }

    @DeleteMapping(TempEndpoints.API_TEMPS)
    public Map<String, Boolean> deleteTemps() {
        return tempService.deleteTemps();
    }
}
