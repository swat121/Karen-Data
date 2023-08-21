package com.karen.controller;

import com.karen.dto.TelegramUserDto;
import com.karen.model.postgres.TelegramUser;
import com.karen.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class UserAPIController {
    private final UserService userService;

    @GetMapping("/api/v1/users")
    public List<TelegramUserDto> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/api/v1/users/name/{name}")
    public TelegramUserDto getUserByName(@PathVariable String name) {
        return userService.getUserByName(name);
    }

    @GetMapping("/api/v1/users/id/{id}")
    public TelegramUserDto getUserByTelegramId(@PathVariable String id) {
        return userService.getUserByTelegramId(id);
    }

    @PostMapping("/api/v1/users")
    public TelegramUserDto setUser(@RequestBody TelegramUser telegramUser) {
        return userService.saveUser(telegramUser);
    }

    @PutMapping("/api/v1/user")
    public int updateUser(@RequestBody TelegramUserDto telegramUser) {
        return userService.updateUser(telegramUser);
    }

    @DeleteMapping("/api/v1/users")
    public void deleteUsers() {
        userService.deleteAllUsers();
    }

    @DeleteMapping("/api/v1/users/name/{name}")
    public void deleteUserByName(@PathVariable String name) {
        userService.deleteUserByName(name);
    }

    @DeleteMapping("/api/v1/users/id/{id}")
    public void deleteUserByTelegramId(@PathVariable String id) {
        userService.deleteUserByTelegramId(id);
    }
}
