package com.karen.repository;

import com.karen.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    Optional<Client> findClientByName(String name);

    @Modifying
    @Transactional
    @Query(value = "update Client c set c.ip = ?1, c.ssid = ?2 where c.name = ?3")
    int updateClientInfo(String ip, String ssid, String name);
}
