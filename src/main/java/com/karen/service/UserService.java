package com.karen.service;

import com.karen.dto.TelegramUserDto;
import com.karen.model.postgres.TelegramUser;
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

    private final Type listType = new TypeToken<List<TelegramUserDto>>() {}.getType();

    public List<TelegramUserDto> getUsers() {
        return modelMapper.map(userRepository.findAll(), listType);
    }

    public TelegramUserDto getUserByName(String name) {
        return modelMapper.map(userRepository.findUserByName(name)
                .orElseThrow(() -> new EntityNotFoundException("Telegram user with name: " + name + " - Not found")), TelegramUserDto.class);
    }

    public TelegramUserDto getUserByTelegramId(String id) {
        return modelMapper.map(userRepository.findUserByTelegramId(id)
                .orElseThrow(() -> new EntityNotFoundException("Telegram user with id: " + id + " - Not found")), TelegramUserDto.class);
    }

    public TelegramUserDto saveUser(TelegramUser telegramUser) {
        checkForDuplicate("telegramId", telegramUser.getTelegramId(), userRepository::findUserByTelegramId);
        checkForDuplicate("name", telegramUser.getName(), userRepository::findUserByName);

        return modelMapper.map(userRepository.save(TelegramUser.builder()
                .name(telegramUser.getName())
                .telegramId(telegramUser.getTelegramId())
                .build()), TelegramUserDto.class);
    }

    public int updateUser(TelegramUserDto telegramUser) {
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

    private void checkForDuplicate(String field, String value, Function<String, Optional<TelegramUser>> finder) {
        if (finder.apply(value).isPresent()) {
            throw new DuplicateKeyException("User with " + field + ": " + value + " already exists");
        }
    }
}
