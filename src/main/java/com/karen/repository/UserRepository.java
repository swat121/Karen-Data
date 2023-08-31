package com.karen.repository;

import com.karen.model.postgres.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByName(String name);

    Optional<User> findUserByTelegramId(String telegramId);

    @Transactional
    void deleteUserByName(String name);

    @Transactional
    void deleteUserByTelegramId(String telegramId);

    @Modifying
    @Transactional
    @Query(value = "update User u set u.telegramId = ?1 where u.name = ?2")
    int updateUserInfo(String ip, String name);
}
