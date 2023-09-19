package com.karen.controller;

import com.karen.dto.BoardConfigDto;
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
    public BoardConfigDto getConfigByBoardName(@PathVariable String name) {
        return boardConfigService.getConfigByBoardName(name);
    }

    @GetMapping("/api/v1/boards")
    public List<BoardConfigDto> getBoardConfigs() {
        return boardConfigService.getConfigs();
    }

    @PostMapping("/api/v1/boards")
    public BoardConfigDto setBoardConfig(@RequestBody BoardConfig boardConfig) {
        return boardConfigService.saveConfig(boardConfig);
    }

    @DeleteMapping("/api/v1/boards")
    public void deleteConfigs() {
        boardConfigService.deleteAllConfigs();
    }

    @DeleteMapping("/api/v1/boards/{name}")
    public void deleteConfigByName(@PathVariable String name) {
        boardConfigService.deleteConfigByName(name);
    }
}
