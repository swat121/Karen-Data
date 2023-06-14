package com.karen.controller;

import com.karen.model.User;
import com.karen.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class UserAPIController {
    private final UserService userService;

    @GetMapping("/api/v1/users")
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/api/v1/users/{name}")
    public User getUserByName(@PathVariable String name) {
        return userService.getUserByName(name);
    }

    @GetMapping("/api/v1/users/{id}")
    public User getUserByTelegramId(@PathVariable String id) {
        return userService.getUserByTelegramId(id);
    }

    @PostMapping("/api/v1/users")
    public User setUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @PutMapping("/api/v1/user")
    public int updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    @DeleteMapping("/api/v1/users")
    public void deleteUsers() {
        userService.deleteAllUsers();
    }

    @DeleteMapping("/api/v1/users/{name}")
    public void deleteUserByName(@PathVariable String name) {
        userService.deleteUserByName(name);
    }

    @DeleteMapping("/api/v1/users/{id}")
    public void deleteUserByTelegramId(@PathVariable String id) {
        userService.deleteUserByTelegramId(id);
    }
}
