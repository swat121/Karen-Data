package com.karen.dto;

import com.karen.model.mongo.Setting;
import lombok.Data;

@Data
public class BoardConfigDto {
    private String name;

    private Setting setting;
}
