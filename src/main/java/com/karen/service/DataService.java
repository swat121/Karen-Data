package com.karen.service;

import com.karen.model.Client;
import com.karen.model.Temperature;
import com.karen.repository.ClientRepository;
import com.karen.repository.TemperatureRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.*;

@Service
@AllArgsConstructor
public class DataService {
    private final TemperatureRepository repository;
    private final ClientRepository clientRepository;

    public Temperature saveTemperature(double degrees) {
        LocalDate myDate = LocalDate.now(ZoneId.of("Europe/Kiev"));
        LocalTime myTime = LocalTime.now(ZoneId.of("Europe/Kiev"));
        return repository.save(Temperature.builder()
                .date(myDate)
                .time(myTime)
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

    public String getIpByName(String name) {
        return clientRepository.findByName(name);
    }

    public Client setClient(String name, String ipAddress) {
        Client client = new Client();
        client.setIp(ipAddress);
        client.setName(name);

//        "Client saved name: " + name + " | ip: " + ipAddress;
        return clientRepository.save(client);
    }
}
