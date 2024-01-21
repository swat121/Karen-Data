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
        return userService.getUsers();
    }

    @GetMapping("/api/v1/users/by-name/{name}")
    public ExternalUserDto getUserByName(@PathVariable String name) {
        return userService.getUserByName(name);
    }

    @GetMapping("/api/v1/users/by-id/{id}")
    public ExternalUserDto getUserByTelegramId(@PathVariable String id) {
        return userService.getUserByTelegramId(id);
    }

    @PostMapping("/api/v1/users")
    public ExternalUserDto setUser(@RequestBody ExternalUserDto externalUserDto) {
        return userService.saveUser(externalUserDto);
    }

    @PutMapping("/api/v1/user")
    public int updateUser(@RequestBody ExternalUserDto externalUserDto) {
        return userService.updateUser(externalUserDto);
    }

    @DeleteMapping("/api/v1/users")
    public void deleteUsers() {
        userService.deleteAllUsers();
    }

    @DeleteMapping("/api/v1/users/by-name/{name}")
    public void deleteUserByName(@PathVariable String name) {
        userService.deleteUserByName(name);
    }

    @DeleteMapping("/api/v1/users/by-id/{id}")
    public void deleteUserByTelegramId(@PathVariable String id) {
        userService.deleteUserByTelegramId(id);
    }
}
