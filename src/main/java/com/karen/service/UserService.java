package com.karen.service;

import com.karen.dto.TelegramUserDto;
import com.karen.model.TelegramUser;
import com.karen.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.lang.reflect.Type;
import java.util.List;

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
}
