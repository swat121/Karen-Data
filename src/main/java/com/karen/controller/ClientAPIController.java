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

    @GetMapping("/clients/{name}")
    public Client getClient(@PathVariable String name) {
        return dataService.getClientByName(name);
    }

    @GetMapping("/clients")
    public List<Client> getClients() {
        return dataService.getAllClients();
    }

    @PostMapping("/clients")
    public Client setClient(@RequestBody Client client) {
        return dataService.setClient(client);
    }

    @PostMapping("/client/update")
    public int updateClient(@RequestBody Client client) {
        return dataService.updateClient(client);
    }

    @DeleteMapping("/clients")
    public void deleteClients() {
        dataService.deleteAllClients();
    }
}
