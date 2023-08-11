package com.karen.service;

import com.karen.model.mongo.BoardConfig;
import com.karen.repository.BoardConfigRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardConfigService {
    private final BoardConfigRepository boardConfigRepository;

    public BoardConfig saveConfig(BoardConfig boardConfig) {
        return boardConfigRepository.save(boardConfig);
    }

    public BoardConfig getConfigById(String id) {
        return boardConfigRepository.findById(id).orElse(null);
    }

    public BoardConfig getConfigByBoardName(String name) {
        return boardConfigRepository.findBoardConfigByName(name);
    }

    public List<BoardConfig> getConfigs() {
        return boardConfigRepository.findAll();
    }

    public void deleteAllConfigs() {
        boardConfigRepository.deleteAll();
    }

    public void deleteConfigByName(String name) {
        boardConfigRepository.deleteBoardConfigByName(name);
    }
}
