package com.karen.controller;

import com.karen.model.mongo.BoardConfig;
import com.karen.service.BoardConfigService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class BoardAPIController {
    private final BoardConfigService boardConfigService;

    @GetMapping("/api/v1/board/{name}")
    public BoardConfig getConfigByBoardName(@PathVariable String name) {
        return boardConfigService.getConfigByBoardName(name);
    }

    @GetMapping("/api/v1/boards")
    public List<BoardConfig> getBoardConfigs() {
        return boardConfigService.getConfigs();
    }

    @GetMapping("/api/v1/board")
    public BoardConfig getConfigById(@RequestParam(value = "id") String id) {
        return boardConfigService.getConfigById(id);
    }

    @PostMapping("/api/v1/boards")
    public BoardConfig setBoardConfig(@RequestBody BoardConfig boardConfig) {
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
