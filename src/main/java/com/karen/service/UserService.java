package com.karen.service;

import com.karen.dto.ExternalUserDto;
import com.karen.model.postgres.ExternalUser;
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

    private final Type listType = new TypeToken<List<ExternalUserDto>>() {
    }.getType();

    public List<ExternalUserDto> getUsers() {
        return modelMapper.map(userRepository.findAll(), listType);
    }

    public ExternalUserDto getUserByName(String name) {
        ExternalUserDto externalUserDto = modelMapper.map(userRepository.findUserByName(name)
                .orElseThrow(() -> new EntityNotFoundException("Telegram user with name: " + name + " - Not found")), ExternalUserDto.class);
        System.out.println();
        System.out.println(externalUserDto);
        System.out.println();
        return externalUserDto;
    }

    public ExternalUserDto getUserByTelegramId(String id) {
        return modelMapper.map(userRepository.findUserByTelegramId(id)
                .orElseThrow(() -> new EntityNotFoundException("Telegram user with id: " + id + " - Not found")), ExternalUserDto.class);
    }

    public ExternalUserDto saveUser(ExternalUser externalUser) {
        checkForDuplicate("telegramId", externalUser.getTelegramId(), userRepository::findUserByTelegramId);
        checkForDuplicate("name", externalUser.getName(), userRepository::findUserByName);

        return modelMapper.map(userRepository.save(ExternalUser.builder()
                .name(externalUser.getName())
                .telegramId(externalUser.getTelegramId())
                .role(externalUser.getRole())
                .isNotify(externalUser.getIsNotify())
                .build()), ExternalUserDto.class);
    }

    public int updateUser(ExternalUserDto telegramUser) {
        return userRepository.updateUserInfo(telegramUser.getTelegramId(), telegramUser.getName(), telegramUser.getIsNotify(), telegramUser.getRole());
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

    private void checkForDuplicate(String field, String value, Function<String, Optional<ExternalUser>> finder) {
        if (finder.apply(value).isPresent()) {
            throw new DuplicateKeyException("User with " + field + ": " + value + " already exists");
        }
    }
}
