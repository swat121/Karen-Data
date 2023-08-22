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
public class ClientAPIController {

    private final ClientService clientService;
    private static final Logger LOG = LogManager.getRootLogger();

    @GetMapping("/api/v1/clients/{name}")
    public ClientDto getClient(@PathVariable String name) {
        LOG.info("GET: client by name = " + name);
        return clientService.getClientByName(name);
    }

    @GetMapping("/api/v1/clients")
    public List<ClientDto> getClients() {
        LOG.info("GET: clients");
        return clientService.getAllClients();
    }

    @PostMapping("/api/v1/clients")
    public ClientDto setClient(@RequestBody ClientDto clientDto) {
        LOG.info("POST: client = " + clientDto);
        return clientService.saveClient(clientDto);
    }

    @PutMapping("/api/v1/client")
    public int updateClient(@RequestBody ClientDto client) {
        LOG.info("PUT: update client = " + client);
        return clientService.updateClient(client);
    }

    @DeleteMapping("/api/v1/clients")
    public void deleteClients() {
        LOG.info("DELETE: clients");
        clientService.deleteAllClients();
    }
}
