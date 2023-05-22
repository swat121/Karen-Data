package com.karen.repository;

import com.karen.model.Timer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TimerRepository extends JpaRepository<Timer, Long> {

    List<Timer> findAllByMicroName(String microName);

    Timer findTimerByMicroNameAndSwitcherName(String microName, String switcherName);

}
