package com.karen.controller;

import com.karen.dto.ClientDto;
import com.karen.service.ClientService;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class ClientController {

    private final ClientService clientService;
    private static final Logger LOG = LogManager.getRootLogger();

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
        return clientService.saveClient(clientDto);
    }

    @PutMapping("/api/v1/client/update")
    public int updateClient(@RequestBody ClientDto client) {
        return clientService.updateClient(client);
    }

    @DeleteMapping("/api/v1/clients")
    public void deleteClients() {
        clientService.deleteAllClients();
    }
}
