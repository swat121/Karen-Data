package com.karen.controller;

import com.karen.model.Client;
import com.karen.service.DataService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class ClientAPIController {

    private final DataService dataService;
    @GetMapping("/clients/{name}")
    public String getClient(@PathVariable String name) {
        return dataService.getIpByName(name);
    }

    @PostMapping("/clients")
    public Client setClient(@RequestBody Client client) {
        return dataService.setClient(client);
    }
}
