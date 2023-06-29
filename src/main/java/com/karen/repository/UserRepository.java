package com.karen.repository;

import com.karen.model.TelegramUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends JpaRepository<TelegramUser, Long> {

    TelegramUser findUserByName(String name);

    TelegramUser findUserByTelegramId(String telegramId);

    @Transactional
    void deleteUserByName(String name);

    @Transactional
    void deleteUserByTelegramId(String telegramId);

    @Modifying
    @Transactional
    @Query(value = "update TelegramUser u set u.telegramId = ?1 where u.name = ?2")
    int updateUserInfo(String ip, String name);
}
