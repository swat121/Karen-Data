package com.karen.service;

import com.karen.dto.BoardConfigDTO;
import com.karen.dto.UserDto;
import com.karen.model.mongo.BoardConfig;
import com.karen.repository.BoardConfigRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class BoardConfigService {
    private final ModelMapper modelMapper;

    private final BoardConfigRepository boardConfigRepository;
    private final Type listType = new TypeToken<List<BoardConfigDTO>>() {}.getType();

    public BoardConfigDTO saveConfig(BoardConfig boardConfig) {
        checkForDuplicate("name", boardConfig.getName(), boardConfigRepository::findBoardConfigByName);
        return modelMapper.map(boardConfigRepository.save(boardConfig), BoardConfigDTO.class);
    }

    public BoardConfigDTO getConfigByBoardName(String name) {
        return modelMapper.map(boardConfigRepository.findBoardConfigByName(name)
                .orElseThrow(() -> new EntityNotFoundException("Board config with name: " + name + " not found")), BoardConfigDTO.class);
    }

    public List<BoardConfigDTO> getConfigs() {
        return modelMapper.map(boardConfigRepository.findAll(), listType);
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
