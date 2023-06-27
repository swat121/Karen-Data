package com.karen.controller;

import com.karen.dto.ClientDto;
import com.karen.model.Client;
import com.karen.service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class ClientAPIController {

    private final ClientService clientService;

    @GetMapping("/api/v1/clients/{name}")
    public ClientDto getClient(@PathVariable String name) {
        return clientService.getClientByName(name);
    }

    @GetMapping("/api/v1/clients")
    public List<ClientDto> getClients() {
        return clientService.getAllClients();
    }

    @PostMapping("/api/v1/clients")
    public ClientDto setClient(@RequestBody ClientDto clientDto) {
        return clientService.setClient(clientDto);
    }

    @PutMapping("/api/v1/client")
    public int updateClient(@RequestBody ClientDto client) {
        return clientService.updateClient(client);
    }

    @DeleteMapping("/api/v1/clients")
    public void deleteClients() {
        clientService.deleteAllClients();
    }
}
