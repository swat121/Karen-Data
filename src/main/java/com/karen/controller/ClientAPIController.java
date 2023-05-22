package com.karen.controller;

import com.karen.model.Client;
import com.karen.service.DataService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class ClientAPIController {

    private final DataService dataService;

    @GetMapping("/api/v1/clients/{name}")
    public Client getClient(@PathVariable String name) {
        return dataService.getClientByName(name);
    }

    @GetMapping("/api/v1/clients")
    public List<Client> getClients() {
        return dataService.getAllClients();
    }

    @PostMapping("/api/v1/clients")
    public Client setClient(@RequestBody Client client) {
        return dataService.setClient(client);
    }

    @PutMapping("/api/v1/client")
    public int updateClient(@RequestBody Client client) {
        return dataService.updateClient(client);
    }

    @DeleteMapping("/api/v1/clients")
    public void deleteClients() {
        dataService.deleteAllClients();
    }
}
