package com.karen.repository;

import com.karen.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByName(String name);

    User findUserByTelegramId(String telegramId);

    void deleteUserByName(String name);

    void deleteUserByTelegramId(String telegramId);

    @Modifying
    @Transactional
    @Query(value = "update User u set u.telegramId = ?1 where u.name = ?2")
    int updateUserInfo(String ip, String name);
}
