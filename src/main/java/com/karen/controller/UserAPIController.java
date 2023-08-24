package com.karen.controller;

import com.karen.dto.TelegramUserDto;
import com.karen.model.postgres.TelegramUser;
import com.karen.service.UserService;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class UserAPIController {
    private final UserService userService;
    private static final Logger LOG = LogManager.getRootLogger();

    @GetMapping("/api/v1/users")
    public List<TelegramUserDto> getUsers() {
        LOG.info("Received GET request on /api/v1/users. Fetching all users.");
        return userService.getUsers();
    }

    @GetMapping("/api/v1/users/name/{name}")
    public TelegramUserDto getUserByName(@PathVariable String name) {
        LOG.info("Received GET request on /api/v1/users/name/{}. Fetching user by name.", name);
        return userService.getUserByName(name);
    }

    @GetMapping("/api/v1/users/id/{id}")
    public TelegramUserDto getUserByTelegramId(@PathVariable String id) {
        LOG.info("Received GET request on /api/v1/users/id/{}. Fetching user by Telegram ID.", id);
        return userService.getUserByTelegramId(id);
    }

    @PostMapping("/api/v1/users")
    public TelegramUserDto setUser(@RequestBody TelegramUser telegramUser) {
        LOG.info("Received POST request on /api/v1/users with user data={}.", telegramUser);
        return userService.saveUser(telegramUser);
    }

    @PutMapping("/api/v1/user")
    public int updateUser(@RequestBody TelegramUserDto telegramUser) {
        LOG.info("Received PUT request on /api/v1/user with user update data={}.", telegramUser);
        return userService.updateUser(telegramUser);
    }

    @DeleteMapping("/api/v1/users")
    public void deleteUsers() {
        LOG.info("Received DELETE request on /api/v1/users. Deleting all users.");
        userService.deleteAllUsers();
    }

    @DeleteMapping("/api/v1/users/name/{name}")
    public void deleteUserByName(@PathVariable String name) {
        LOG.info("Received DELETE request on /api/v1/users/name/{}. Deleting user by name.", name);
        userService.deleteUserByName(name);
    }

    @DeleteMapping("/api/v1/users/id/{id}")
    public void deleteUserByTelegramId(@PathVariable String id) {
        LOG.info("Received DELETE request on /api/v1/users/id/{}. Deleting user by Telegram ID.", id);
        userService.deleteUserByTelegramId(id);
    }
}
