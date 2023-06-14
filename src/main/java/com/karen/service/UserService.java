package com.karen.service;

import com.karen.model.User;
import com.karen.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUserByName(String name) {
        return userRepository.findUserByName(name);
    }

    public User getUserByTelegramId(String id) {
        return userRepository.findUserByTelegramId(id);
    }

    public User addUser(User user) {
        return userRepository.save(User.builder()
                .name(user.getName())
                .telegramId(user.getTelegramId())
                .build());
    }

    public int updateUser(User user) {
        return userRepository.updateUserInfo(user.getTelegramId(), user.getName());
    }

    public void deleteAllUsers() {
        userRepository.deleteAll();
    }

    public void deleteUserByName(String name) {
        userRepository.deleteUserByName(name);
    }

    public void deleteUserByTelegramId(String id) {
        userRepository.deleteUserByTelegramId(id);
    }
}
