package com.karen.service;

import com.karen.model.Temperature;
import com.karen.repository.TemperatureRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class DataService {
    private final TemperatureRepository repository;
    private Date currentDate;

    public Temperature saveTemperature(double degrees) {
        currentDate = new Date();
        return repository.save(Temperature.builder()
                .date(currentDate)
                .time(currentDate)
                .degreesCelsius(degrees)
                .build());
    }

    public List<Temperature> getTemperature() {
        return repository.findAll();
    }

    public Map<String, Boolean> deleteTemps() {
        repository.deleteAll();
        Map<String, Boolean> response = new HashMap<>();
        response.put("Delete", Boolean.TRUE);
        return response;
    }
}
