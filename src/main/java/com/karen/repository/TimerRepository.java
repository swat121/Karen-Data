package com.karen.repository;

import com.karen.model.postgres.Timer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface TimerRepository extends JpaRepository<Timer, Long> {

    List<Timer> findAllByMicroName(String microName);

    Optional<Timer> findTimerByMicroNameAndSwitcherName(String microName, String switcherName);

    void deleteByMicroName(String microName);

    @Modifying
    @Transactional
    @Query(value = "update Timer t set t.status = ?1 where t.microName = ?2 and t.switcherName = ?3")
    int updateTimerInfo(String status, String microName, String switcherName);
}
