package com.karen.repository;

import com.karen.model.postgres.ExternalUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserRepository extends JpaRepository<ExternalUser, Long> {

    Optional<ExternalUser> findUserByName(String name);

    Optional<ExternalUser> findUserByTelegramId(String telegramId);

    @Transactional
    void deleteUserByName(String name);

    @Transactional
    void deleteUserByTelegramId(String telegramId);

    @Modifying
    @Transactional
    @Query(value = "update ExternalUser u set u.telegramId = ?1, u.isNotify = ?3, u.role = ?4 where u.name = ?2")
    int updateUserInfo(String ip, String name, Boolean isNotify, String role);
}
