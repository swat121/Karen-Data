package com.karen.service;

import com.karen.dto.ClientDto;
import com.karen.model.postgres.Client;
import com.karen.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.lang.reflect.Type;
import java.util.*;
import java.util.function.Function;

@Service
@AllArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;
    private final ModelMapper modelMapper;
    private final Type listType = new TypeToken<List<ClientDto>>() {
    }.getType();

    public ClientDto getClientByName(String name) {
        return modelMapper.map(clientRepository.findClientByName(name)
                .orElseThrow(() -> new EntityNotFoundException("Client with name: " + name + " - Not found")), ClientDto.class);
    }

    public List<ClientDto> getAllClients() {
        return modelMapper.map(clientRepository.findAll(), listType);
    }

    public ClientDto saveClient(ClientDto clientDto) {
        checkForDuplicate("name", clientDto.getName(), clientRepository::findClientByName);
        checkForDuplicate("ip", clientDto.getIp(), clientRepository::findClientByIp);
        checkForDuplicate("mac", clientDto.getMac(), clientRepository::findClientByMac);

        Client client = Client.builder()
                .ip(clientDto.getIp())
                .mac(clientDto.getMac())
                .ssid(clientDto.getSsid())
                .name(clientDto.getName())
                .version(clientDto.getVersion())
                .build();
        return modelMapper.map(clientRepository.save(client), ClientDto.class);
    }

    public int updateClient(ClientDto client) {
        return clientRepository.updateClientInfo(client.getIp(), client.getSsid(), client.getName());
    }

    public void deleteAllClients() {
        clientRepository.deleteAll();
    }

    private void checkForDuplicate(String field, String value, Function<String, Optional<Client>> finder) {
        if (finder.apply(value).isPresent()) {
            throw new DuplicateKeyException("Client with " + field + ": " + value + " already exists");
        }
    }
}
