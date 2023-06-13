package com.karen.repository;

import com.karen.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByName(String name);

    User findUserByTelegramId(String telegramId);

    void deleteUserByName(String name);

    void deleteUserByTelegramId(String telegramId);
}
