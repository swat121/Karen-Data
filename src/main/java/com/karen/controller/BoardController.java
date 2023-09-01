package com.karen.controller;

import com.karen.dto.BoardConfigDTO;
import com.karen.model.mongo.BoardConfig;
import com.karen.service.BoardConfigService;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class BoardController {
    private final BoardConfigService boardConfigService;
    private static final Logger LOG = LogManager.getRootLogger();

    @GetMapping("/api/v1/boards/{name}")
    public BoardConfigDTO getConfigByBoardName(@PathVariable String name) {
        LOG.info("Received GET request on /api/v1/boards/{}. Fetching board config by name.", name);
        return boardConfigService.getConfigByBoardName(name);
    }

    @GetMapping("/api/v1/boards")
    public List<BoardConfigDTO> getBoardConfigs() {
        LOG.info("Received GET request on /api/v1/boards. Fetching list of board configs.");
        return boardConfigService.getConfigs();
    }

    @PostMapping("/api/v1/boards")
    public BoardConfigDTO setBoardConfig(@RequestBody BoardConfig boardConfig) {
        LOG.info("Received POST request on /api/v1/boards with board config data={}.", boardConfig);
        return boardConfigService.saveConfig(boardConfig);
    }

    @DeleteMapping("/api/v1/boards")
    public void deleteConfigs() {
        LOG.info("Received DELETE request on /api/v1/boards. Deleting all board configs.");
        boardConfigService.deleteAllConfigs();
    }

    @DeleteMapping("/api/v1/boards/{name}")
    public void deleteConfigByName(@PathVariable String name) {
        LOG.info("Received DELETE request on /api/v1/boards/{}. Deleting board config by name.", name);
        boardConfigService.deleteConfigByName(name);
    }
}
