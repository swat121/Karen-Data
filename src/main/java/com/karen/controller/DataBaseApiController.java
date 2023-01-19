package com.karen.controller;

import com.karen.model.Temperature;
import com.karen.service.DateService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
public class DataBaseApiController {

    private final DateService dateService;

    @PostMapping("/api/temp")
    public Temperature addTemp(@RequestParam(value = "degrees", required = false) int degrees) {
        return dateService.saveTemperature(degrees);
    }

    @GetMapping("/api/temps")
    public List<Temperature> getTemps() {
        return dateService.getTemperature();
    }

    @DeleteMapping("/api/temps")
    public Map<String, Boolean> deleteTemps() {
        return dateService.deleteTemps();
    }
}
