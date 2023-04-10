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
    public Client setClient(@RequestParam(value = "ip", required = true) String ip, @RequestParam(value = "name", required = true) String name) {
        return dataService.setClient(name, ip);
    }
}
