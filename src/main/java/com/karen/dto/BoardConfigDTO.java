package com.karen.dto;

import com.karen.model.mongo.Setting;
import lombok.Builder;
import lombok.Data;

@Data
public class BoardConfigDTO {
    private String name;

    private Setting setting;
}
