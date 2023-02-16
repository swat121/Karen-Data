package com.karen.controller;

import com.karen.endpoints.TempEndpoints;
import com.karen.model.Temperature;
import com.karen.service.DataService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
public class DataBaseApiController {

    private final DataService dataService;

    @PostMapping(TempEndpoints.API_TEMP)
    public Temperature addTemp(@RequestParam(value = "degrees", required = false) int degrees) {
        return dataService.saveTemperature(degrees);
    }

    @GetMapping(TempEndpoints.API_TEMPS)
    public List<Temperature> getTemps() {
        return dataService.getTemperature();
    }

    @DeleteMapping(TempEndpoints.API_TEMPS)
    public Map<String, Boolean> deleteTemps() {
        return dataService.deleteTemps();
    }
}
