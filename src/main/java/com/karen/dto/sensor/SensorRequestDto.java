package com.karen.dto.sensor;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SensorRequestDto {
    private String name;
    private String sensorId;
    private String data;
}
