package com.karen.controller;

import com.karen.dto.ExternalUserDto;
import com.karen.model.postgres.ExternalUser;
import com.karen.service.UserService;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class UserController {
    private final UserService userService;
    private static final Logger LOG = LogManager.getRootLogger();

    @GetMapping("/api/v1/users")
    public List<ExternalUserDto> getUsers() {
        LOG.info("Received GET request on /api/v1/users. Fetching all users.");
        return userService.getUsers();
    }

    @GetMapping("/api/v1/users/by-name/{name}")
    public ExternalUserDto getUserByName(@PathVariable String name) {
        LOG.info("Received GET request on /api/v1/users/by-name/{}. Fetching user by name.", name);
        return userService.getUserByName(name);
    }

    @GetMapping("/api/v1/users/by-id/{id}")
    public ExternalUserDto getUserByTelegramId(@PathVariable String id) {
        LOG.info("Received GET request on /api/v1/users/by-id/{}. Fetching user by Telegram ID.", id);
        return userService.getUserByTelegramId(id);
    }

    @PostMapping("/api/v1/users")
    public ExternalUserDto setUser(@RequestBody ExternalUser externalUser) {
        LOG.info("Received POST request on /api/v1/users with user data={}.", externalUser);
        return userService.saveUser(externalUser);
    }

    @PutMapping("/api/v1/user")
    public int updateUser(@RequestBody ExternalUserDto externalUserDto) {
        LOG.info("Received PUT request on /api/v1/user with user update data={}.", externalUserDto);
        return userService.updateUser(externalUserDto);
    }

    @DeleteMapping("/api/v1/users")
    public void deleteUsers() {
        LOG.info("Received DELETE request on /api/v1/users. Deleting all users.");
        userService.deleteAllUsers();
    }

    @DeleteMapping("/api/v1/users/by-name/{name}")
    public void deleteUserByName(@PathVariable String name) {
        LOG.info("Received DELETE request on /api/v1/users/name/{}. Deleting user by name.", name);
        userService.deleteUserByName(name);
    }

    @DeleteMapping("/api/v1/users/by-id/{id}")
    public void deleteUserByTelegramId(@PathVariable String id) {
        LOG.info("Received DELETE request on /api/v1/users/id/{}. Deleting user by Telegram ID.", id);
        userService.deleteUserByTelegramId(id);
    }
}
