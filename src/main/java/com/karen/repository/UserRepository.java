package com.karen.repository;

import com.karen.model.postgres.TelegramUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserRepository extends JpaRepository<TelegramUser, Long> {

    Optional<TelegramUser> findUserByName(String name);

    Optional<TelegramUser> findUserByTelegramId(String telegramId);

    @Transactional
    void deleteUserByName(String name);

    @Transactional
    void deleteUserByTelegramId(String telegramId);

    @Modifying
    @Transactional
    @Query(value = "update TelegramUser u set u.telegramId = ?1 where u.name = ?2")
    int updateUserInfo(String ip, String name);
}
