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
        LOG.info("GET: users");
        return userService.getUsers();
    }

    @GetMapping("/api/v1/users/name/{name}")
    public TelegramUserDto getUserByName(@PathVariable String name) {
        LOG.info("GET: user by name = " + name);
        return userService.getUserByName(name);
    }

    @GetMapping("/api/v1/users/id/{id}")
    public TelegramUserDto getUserByTelegramId(@PathVariable String id) {
        LOG.info("GET: user by id = " + id);
        return userService.getUserByTelegramId(id);
    }

    @PostMapping("/api/v1/users")
    public TelegramUserDto setUser(@RequestBody TelegramUser telegramUser) {
        LOG.info("POST: user = " + telegramUser);
        return userService.saveUser(telegramUser);
    }

    @PutMapping("/api/v1/user")
    public int updateUser(@RequestBody TelegramUserDto telegramUser) {
        LOG.info("PUT: update user = " + telegramUser);
        return userService.updateUser(telegramUser);
    }

    @DeleteMapping("/api/v1/users")
    public void deleteUsers() {
        LOG.info("DELETE: users");
        userService.deleteAllUsers();
    }

    @DeleteMapping("/api/v1/users/name/{name}")
    public void deleteUserByName(@PathVariable String name) {
        LOG.info("DELETE: user by name = " + name);
        userService.deleteUserByName(name);
    }

    @DeleteMapping("/api/v1/users/id/{id}")
    public void deleteUserByTelegramId(@PathVariable String id) {
        LOG.info("DELETE: user by id = " + id);
        userService.deleteUserByTelegramId(id);
    }
}
