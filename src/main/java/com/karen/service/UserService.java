package com.karen.service;

import com.karen.dto.UserDto;
import com.karen.model.postgres.User;
import com.karen.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
@AllArgsConstructor
public class UserService {
    private final ModelMapper modelMapper;

    private final UserRepository userRepository;

    private final Type listType = new TypeToken<List<UserDto>>() {
    }.getType();

    public List<UserDto> getUsers() {
        return modelMapper.map(userRepository.findAll(), listType);
    }

    public UserDto getUserByName(String name) {
        return modelMapper.map(userRepository.findUserByName(name)
                .orElseThrow(() -> new EntityNotFoundException("Telegram user with name: " + name + " - Not found")), UserDto.class);
    }

    public UserDto getUserByTelegramId(String id) {
        return modelMapper.map(userRepository.findUserByTelegramId(id)
                .orElseThrow(() -> new EntityNotFoundException("Telegram user with id: " + id + " - Not found")), UserDto.class);
    }

    public UserDto saveUser(User user) {
        checkForDuplicate("telegramId", user.getTelegramId(), userRepository::findUserByTelegramId);
        checkForDuplicate("name", user.getName(), userRepository::findUserByName);

        return modelMapper.map(userRepository.save(User.builder()
                .name(user.getName())
                .telegramId(user.getTelegramId())
                .build()), UserDto.class);
    }

    public int updateUser(UserDto telegramUser) {
        return userRepository.updateUserInfo(telegramUser.getTelegramId(), telegramUser.getName());
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

    private void checkForDuplicate(String field, String value, Function<String, Optional<User>> finder) {
        if (finder.apply(value).isPresent()) {
            throw new DuplicateKeyException("User with " + field + ": " + value + " already exists");
        }
    }
}
