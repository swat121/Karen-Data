package com.karen.dto.sensor;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SensorResponseDto {
    private String name;
    private String sensorId;
    private String data;
    private LocalDate date;

    private LocalTime time;
}
