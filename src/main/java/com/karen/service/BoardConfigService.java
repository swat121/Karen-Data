package com.karen.service;

import com.karen.model.mongo.BoardConfig;
import com.karen.repository.BoardConfigRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class BoardConfigService {
    private final BoardConfigRepository boardConfigRepository;

    public BoardConfig saveConfig(BoardConfig boardConfig) {
        checkForDuplicate("name", boardConfig.getName(), boardConfigRepository::findBoardConfigByName);
        return boardConfigRepository.save(boardConfig);
    }

    public BoardConfig getConfigById(String id) {
        return boardConfigRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Board config with id: " + id + " not found"));
    }

    public BoardConfig getConfigByBoardName(String name) {
        return boardConfigRepository.findBoardConfigByName(name).orElseThrow(() -> new EntityNotFoundException("Board config with name: " + name + " not found"));
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

    private void checkForDuplicate(String field, String value, Function<String, Optional<BoardConfig>> finder) {
        if (finder.apply(value).isPresent()) {
            throw new DuplicateKeyException("Board with " + field + ": " + value + " already exists");
        }
    }
}
