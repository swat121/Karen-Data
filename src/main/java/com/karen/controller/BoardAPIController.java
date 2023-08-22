package com.karen.controller;

import com.karen.model.mongo.BoardConfig;
import com.karen.service.BoardConfigService;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class BoardAPIController {
    private final BoardConfigService boardConfigService;
    private static final Logger LOG = LogManager.getRootLogger();
    private static final Marker IMPORTANT_MARKER = MarkerFactory.getMarker("IMPORTANT");

    @GetMapping("/api/v1/boards/{name}")
    public BoardConfig getConfigByBoardName(@PathVariable String name) {
        LOG.info("GET: board config be name = " + name, IMPORTANT_MARKER);
        return boardConfigService.getConfigByBoardName(name);
    }

    @GetMapping("/api/v1/boards")
    public List<BoardConfig> getBoardConfigs() {
        LOG.info("GET: list of board configs");
        return boardConfigService.getConfigs();
    }

    @GetMapping("/api/v1/board")
    public BoardConfig getConfigById(@RequestParam(value = "id") String id) {
        LOG.info("GET: board config by id = " + id);
        return boardConfigService.getConfigById(id);
    }

    @PostMapping("/api/v1/boards")
    public BoardConfig setBoardConfig(@RequestBody BoardConfig boardConfig) {
        LOG.info("POST: board config = " + boardConfig);
        return boardConfigService.saveConfig(boardConfig);
    }

    @DeleteMapping("/api/v1/boards")
    public void deleteConfigs() {
        LOG.info("DELETE: all board configs");
        boardConfigService.deleteAllConfigs();
    }

    @DeleteMapping("/api/v1/boards/{name}")
    public void deleteConfigByName(@PathVariable String name) {
        LOG.info("DELETE: board config by name = " + name);
        boardConfigService.deleteConfigByName(name);
    }
}
