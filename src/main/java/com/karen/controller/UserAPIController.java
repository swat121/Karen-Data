package com.karen.controller;

import com.karen.dto.TelegramUserDto;
import com.karen.model.TelegramUser;
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

    @GetMapping("/api/v1/users/{name}")
    public TelegramUserDto getUserByName(@PathVariable String name) {
        return userService.getUserByName(name);
    }

    @GetMapping("/api/v1/user")
    public TelegramUserDto getUserByTelegramId(@RequestParam(value = "id") String id) {
        return userService.getUserByTelegramId(id);
    }

    @PostMapping("/api/v1/users")
    public TelegramUserDto setUser(@RequestBody TelegramUser telegramUser) {
        return userService.addUser(telegramUser);
    }

    @PutMapping("/api/v1/user")
    public int updateUser(@RequestBody TelegramUserDto telegramUser) {
        return userService.updateUser(telegramUser);
    }

    @DeleteMapping("/api/v1/users")
    public void deleteUsers() {
        userService.deleteAllUsers();
    }

    @DeleteMapping("/api/v1/users/{name}")
    public void deleteUserByName(@PathVariable String name) {
        userService.deleteUserByName(name);
    }

    @DeleteMapping("/api/v1/user")
    public void deleteUserByTelegramId(@RequestParam(value = "id") String id) {
        userService.deleteUserByTelegramId(id);
    }
}
